package io.nuls.dapp.communitygovernance.service.api;

import io.nuls.dapp.communitygovernance.config.ServerContext;
import io.nuls.dapp.communitygovernance.model.account.AccountPo;
import io.nuls.dapp.communitygovernance.util.AppUtil;
import io.nuls.v2.model.dto.RpcResult;
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
        int chainId = ServerContext.chainId;
        int assetId = ServerContext.assetId;
        RpcResult<Map> result = AppUtil.jsonRpcRequest("getAccountBalance", ListUtil.of(chainId, assetId, assetId, address), 5);
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

    /**
     * 根据地址获取别名
     * @param address
     * @return
     * @throws InterruptedException
     */
    public String getAddressAlias(String address) throws InterruptedException {
        String alias = null;
        int chainId = ServerContext.chainId;
        RpcResult<Map> result = AppUtil.jsonRpcRequest(ServerContext.public_service_url,"getAccount", ListUtil.of(chainId, address));
        if(result == null) {
            logger.error("Post time out times!!! address is {}", address);
            return alias;
        }
        if(result.getError() != null) {
            logger.error("getAccount error, address is {}, error is {}", address, result.getError().toString());
            return alias;
        }
        Map info = result.getResult();

        if(info != null) {
            alias = info.get("alias").toString();
        }
        return alias;
    }
}
