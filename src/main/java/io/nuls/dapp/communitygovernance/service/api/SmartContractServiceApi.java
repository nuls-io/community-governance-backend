package io.nuls.dapp.communitygovernance.service.api;

import io.nuls.dapp.communitygovernance.config.ServerContext;
import io.nuls.v2.model.dto.RpcResult;
import io.nuls.v2.util.JsonRpcUtil;
import io.nuls.v2.util.ListUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 智能合约接口调用
 * Created by wangkun23 on 2018/9/3.
 *
 * @author wangkun23
 * @date 2018/9/3.
 */
@Service
public class SmartContractServiceApi {
    final Logger logger = LoggerFactory.getLogger(getClass());

    public RpcResult<Map<String, Map>> getContractTxResultList(List<String> hashList) {
        RpcResult<Map<String, Map>> rpcResult = JsonRpcUtil.request("getContractTxResultList", ListUtil.of(ServerContext.chainId, hashList));
        return rpcResult;
    }


}
