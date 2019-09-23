package io.nuls.dapp.communitygovernance.config;

import io.nuls.dapp.communitygovernance.util.AppUtil;
import io.nuls.v2.NulsSDKBootStrap;
import io.nuls.v2.SDKContext;
import io.nuls.v2.model.dto.RpcResult;
import io.nuls.v2.util.ListUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 启动时做一些操作
 * @author: PierreLuo
 * @date: 2019-08-12
 */
@Component
public class AppInitializing implements InitializingBean {

    final Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${app.contract.address}")
    private String contractAddress;
    @Value("${app.nulsApi.host}")
    private String nulsApiHost;
    @Value("${app.publicService.host}")
    private String publicServiceHost;

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("contract address is {}", contractAddress);
        logger.info("nuls api host is {}", nulsApiHost);
        logger.info("public service host is {}", publicServiceHost);
        ServerContext.public_service_url = publicServiceHost;
        ServerContext.contract_address = contractAddress;
        SDKContext.wallet_url = nulsApiHost;
        RpcResult info = AppUtil.jsonRpcRequest("info", ListUtil.of());
        Map result = (Map) info.getResult();
        Integer chainId = (Integer) result.get("chainId");
        ServerContext.chainId = chainId != null ? chainId : ServerContext.chainId;
        Integer assetId = (Integer) result.get("assetId");
        ServerContext.assetId = assetId != null ? assetId : ServerContext.assetId;
        // initial SDK
        NulsSDKBootStrap.init(ServerContext.chainId, nulsApiHost);
    }

}
