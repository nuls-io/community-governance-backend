package io.nuls.dapp.communitygovernance.service.api;

import com.alibaba.fastjson.JSONObject;
import io.nuls.base.basic.NulsByteBuffer;
import io.nuls.base.data.Block;
import io.nuls.core.crypto.HexUtil;
import io.nuls.core.exception.NulsException;
import io.nuls.dapp.communitygovernance.config.ServerContext;
import io.nuls.dapp.communitygovernance.model.SimpleBlockHeader;
import io.nuls.v2.model.dto.RpcResult;
import io.nuls.v2.util.JsonRpcUtil;
import io.nuls.v2.util.ListUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * 区块接口服务
 * Created by wangkun23 on 2018/9/5.
 */
@Service
public class BlockServiceApi {

    final Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * 钱包接口地址
     */
    @Value("${app.provider.host}")
    private String host;

    @Resource
    private RestTemplate restTemplate;


    /**
     * 获取最新区块头
     *
     * @return
     */
    public SimpleBlockHeader getNewestBlockHeader() {
        String url = host + "/api/block/header/newest";
        JSONObject result = restTemplate.getForObject(url, JSONObject.class);
        logger.info("result {}", result);
        if (result.getBoolean("success")) {
            JSONObject data = result.getJSONObject("data");
            Long height = data.getLong("value");
            SimpleBlockHeader header = new SimpleBlockHeader();
            header.setHash(data.getString("hash"));
            header.setPreHash(data.getString("preHash"));
            header.setHeight(data.getLong("height"));
            return header;
        } else {
            logger.error("result {}", result);
            return null;
        }
    }

    /**
     * 根据高度获取区块
     *
     * @return
     */
    public Block getBlockByHeight(Long height) throws NulsException {
        RpcResult rpcResult = JsonRpcUtil.request("getBlockSerializationByHeight", ListUtil.of(ServerContext.chainId, height));
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
    public Block getBlockByHash(String hash) throws NulsException {
        RpcResult rpcResult = JsonRpcUtil.request("getBlockSerializationByHash", ListUtil.of(ServerContext.chainId, hash));
        String blockHex = (String) rpcResult.getResult();
        Block block = new Block();
        block.parse(new NulsByteBuffer(HexUtil.decode(blockHex)));
        return block;
    }
}
