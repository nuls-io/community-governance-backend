package io.nuls.dapp.communitygovernance.service.api;

import io.nuls.dapp.communitygovernance.config.ServerContext;
import io.nuls.dapp.communitygovernance.model.account.AccountPo;
import io.nuls.v2.model.dto.RpcResult;
import io.nuls.v2.util.JsonRpcUtil;
import io.nuls.v2.util.ListUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author: PierreLuo
 * @date: 2019-08-09
 */
@Service
public class AccountServiceApi {
    final Logger logger = LoggerFactory.getLogger(getClass());

    /***
     * 查询用户余额
     * @param address
     * @return
     */
    public AccountPo getAccountBalance(String address) throws InterruptedException {
        String alias = null;
        BigDecimal balance = BigDecimal.ZERO;

        RpcResult<Map> result = postData(address);
        if(result == null) {
            result = postData(address);
            if(result == null) {
                result = postData(address);
            }
        }
        if(result == null) {
            logger.error("Post time out 6 times!!! address is {}", address);
            AccountPo po = new AccountPo(BigDecimal.ZERO, alias);
            return po;
        }
        if(result.getError() != null) {
            logger.error("getAccountBalance error, address is {}, error is {}", address, result.getError().toString());
            AccountPo po = new AccountPo(BigDecimal.ZERO, alias);
            return po;
        }

        Map info = result.getResult();

        if(info != null) {
            balance = new BigDecimal(info.get("totalBalance").toString());
        }
        logger.info("result is {}", result);

        AccountPo po = new AccountPo(balance, alias);
        return po;
    }

    private RpcResult<Map> postData(String address) throws InterruptedException {
        RpcResult rpcResult = null;
        int chainId = ServerContext.chainId;
        int assetId = ServerContext.assetId;
        try {
            rpcResult = JsonRpcUtil.request("getAccountBalance", ListUtil.of(chainId, assetId, assetId, address));
            return rpcResult;
        } catch (Exception e) {
            logger.info("post time out, address is {}", address);
            Thread.sleep(500);
            try {
                rpcResult = JsonRpcUtil.request("getAccountBalance", ListUtil.of(chainId, assetId, assetId, address));
            } catch (Exception e1) {
                logger.info("post time out, address is {}", address);
                Thread.sleep(500);
            }
            return rpcResult;
        }

    }
}
