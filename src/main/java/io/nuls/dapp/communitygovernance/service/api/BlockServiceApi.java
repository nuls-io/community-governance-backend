package io.nuls.dapp.communitygovernance.service.api;

import io.nuls.base.basic.NulsByteBuffer;
import io.nuls.base.data.Block;
import io.nuls.core.crypto.HexUtil;
import io.nuls.dapp.communitygovernance.config.ServerContext;
import io.nuls.dapp.communitygovernance.model.SimpleBlockHeader;
import io.nuls.dapp.communitygovernance.util.AppUtil;
import io.nuls.v2.model.dto.RpcResult;
import io.nuls.v2.util.ListUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 区块接口服务
 * Created by wangkun23 on 2018/9/5.
 */
@Service
public class BlockServiceApi {

    final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 获取最新区块头
     *
     * @return
     */
    public SimpleBlockHeader getNewestBlockHeader() throws Exception {
        RpcResult rpcResult = AppUtil.jsonRpcRequest("getBestBlockHeader", ListUtil.of(ServerContext.chainId));
        if(rpcResult == null) {
            logger.error("empty block about getting newest block!!!");
            throw new Exception("empty Block");
        }
        if(rpcResult.getError() != null) {
            logger.error("error block about getting newest block !!! - {[]}", rpcResult.getError().toString());
            throw new Exception(rpcResult.getError().toString());
        }
        Map result = (Map) rpcResult.getResult();
        SimpleBlockHeader header = new SimpleBlockHeader();
        header.setHash((String) result.get("hash"));
        header.setPreHash((String) result.get("preHash"));
        header.setHeight(Long.parseLong(result.get("height").toString()));
        return header;
    }

    /**
     * 根据高度获取区块
     *
     * @return
     */
    public Block getBlockByHeight(Long height) throws Exception {
        RpcResult rpcResult = AppUtil.jsonRpcRequest("getBlockSerializationByHeight", ListUtil.of(ServerContext.chainId, height));
        if(rpcResult == null) {
            logger.error("empty block about getting block by height!!!");
            throw new Exception("empty Block");
        }
        if(rpcResult.getError() != null) {
            logger.error("error block about getting block by height!!! - {[]}", rpcResult.getError().toString());
            throw new Exception(rpcResult.getError().toString());
        }
        String blockHex = (String) rpcResult.getResult();
        Block block = new Block();
        block.parse(new NulsByteBuffer(HexUtil.decode(blockHex)));
        return block;
    }

    /**
     * 根据hash获取区块
     *
     * @return
     */
    public Block getBlockByHash(String hash) throws Exception {
        RpcResult rpcResult = AppUtil.jsonRpcRequest("getBlockSerializationByHash", ListUtil.of(ServerContext.chainId, hash));
        String blockHex = (String) rpcResult.getResult();
        Block block = new Block();
        block.parse(new NulsByteBuffer(HexUtil.decode(blockHex)));
        return block;
    }
}
