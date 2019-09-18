package io.nuls.dapp.communitygovernance.service;

import com.alibaba.fastjson.JSON;
import io.nuls.base.RPCUtil;
import io.nuls.base.basic.NulsByteBuffer;
import io.nuls.base.data.Block;
import io.nuls.base.data.BlockHeader;
import io.nuls.base.data.CoinData;
import io.nuls.base.data.Transaction;
import io.nuls.core.constant.TxType;
import io.nuls.core.exception.NulsException;
import io.nuls.dapp.communitygovernance.model.SimpleBlockHeader;
import io.nuls.dapp.communitygovernance.model.contract.EventJson;
import io.nuls.dapp.communitygovernance.service.api.BlockServiceApi;
import io.nuls.dapp.communitygovernance.service.api.SmartContractServiceApi;
import io.nuls.v2.model.dto.RpcResult;
import io.nuls.v2.txdata.CallContractData;
import io.nuls.v2.txdata.ContractData;
import io.nuls.v2.txdata.CreateContractData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * 区块同步
 *
 * @author: PierreLuo
 * @date: 2019-08-09
 */
@Service
public class BlockSyncService {

    final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    BlockServiceApi blockServiceApi;
    @Resource
    SmartContractServiceApi smartContractServiceApi;

    private List<IEventProcessor> eventProcessorList = new ArrayList<>();
    private List<IAccountBalanceProcessor> accountBalanceProcessorList = new ArrayList<>();

    public void addEventProcessor(IEventProcessor processor) {
        this.eventProcessorList.add(processor);
    }

    public void addAccountBalanceProcessor(IAccountBalanceProcessor processor) {
        this.accountBalanceProcessorList.add(processor);
    }

    /**
     * 解析区块
     */
    public SimpleBlockHeader syncBlock(Long height) throws Exception {
        Block block = blockServiceApi.getBlockByHeight(height);
        BlockHeader header = block.getHeader();
        List<Transaction> txs = block.getTxs();
        List<String> hashList = new ArrayList<>();
        Map<String, Transaction> contractTxMap = new HashMap<>();
        int txType;
        String txHash;
        for (Transaction tx : txs) {
            txType = tx.getType();
            if (txType == TxType.CREATE_CONTRACT ||
                    txType == TxType.CALL_CONTRACT ||
                    txType == TxType.DELETE_CONTRACT) {
                txHash = tx.getHash().toHex();
                contractTxMap.put(txHash, tx);
                hashList.add(txHash);
            }
        }
        do {
            if(hashList.isEmpty()) {
                break;
            }
            RpcResult<Map<String, Map>> rpcResult = smartContractServiceApi.getContractTxResultList(hashList);
            if(rpcResult.getError() != null) {
                throw new RuntimeException(rpcResult.getError().toString());
            }
            Map<String, Map> result = rpcResult.getResult();
            if(result != null) {
                Set<Map.Entry<String, Map>> entries = result.entrySet();
                for(Map.Entry<String, Map> entry : entries) {
                    String hash = entry.getKey();
                    Map contractResult = entry.getValue();
                    List<String> contractTxList = (List<String>) contractResult.get("contractTxList");
                    // 增加合约生成交易，使区块交易补充完整
                    for (String txHex : contractTxList) {
                        Transaction cgtx = new Transaction();
                        cgtx.parse(new NulsByteBuffer(RPCUtil.decode(txHex)));
                        cgtx.setBlockHeight(header.getHeight());
                        txs.add(cgtx);
                    }
                    // 处理合约事件
                    List<String> events = (List<String>) contractResult.get("events");
                    if(events.isEmpty()) {
                        continue;
                    }
                    Transaction ctx = contractTxMap.get(hash);
                    ContractData contractData = parseContractTxData(ctx);
                    int type = ctx.getType();
                    for (String event : events) {
                        EventJson eventJson = JSON.parseObject(event, EventJson.class);
                        // 合约事件处理器
                        for(IEventProcessor processor : eventProcessorList) {
                            processor.execute(hash, type, contractData, eventJson);
                        }
                    }
                }
            }
        } while (false);
        // 处理交易中的余额变动
        CoinData coinData;
        for(Transaction tx : txs) {
            byte[] txCoinData = tx.getCoinData();
            if(txCoinData == null) {
                continue;
            }
            coinData = tx.getCoinDataInstance();
            int type = tx.getType();
            // 账户余额处理器
            for(IAccountBalanceProcessor processor : accountBalanceProcessorList) {
                processor.execute(type, coinData);
            }
        }
        SimpleBlockHeader simpleBlockHeader = new SimpleBlockHeader();
        simpleBlockHeader.setHeight(height);
        simpleBlockHeader.setHash(header.getHash().toHex());
        simpleBlockHeader.setPreHash(header.getPreHash().toHex());
        return simpleBlockHeader;
    }

    private ContractData parseContractTxData(Transaction tx) throws NulsException {
        int txType = tx.getType();
        if(txType == TxType.CREATE_CONTRACT) {
            CreateContractData data = new CreateContractData();
            data.parse(new NulsByteBuffer(tx.getTxData()));
            return data;
        } else if(txType == TxType.CALL_CONTRACT) {
            CallContractData data = new CallContractData();
            data.parse(new NulsByteBuffer(tx.getTxData()));
            return data;
        } else {
            return null;
        }
    }

}
