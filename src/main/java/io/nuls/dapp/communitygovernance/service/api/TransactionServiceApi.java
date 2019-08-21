package io.nuls.dapp.communitygovernance.service.api;

import com.alibaba.fastjson.JSONObject;
import io.nuls.dapp.communitygovernance.constant.ResponseKey;
import io.nuls.v2.model.dto.TransactionDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author: PierreLuo
 * @date: 2019-08-09
 */
@Service
public class TransactionServiceApi {

    final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * wallet接口调用地址
     */
    @Value("${app.provider.host}")
    private String host;

    @Resource
    private RestTemplate restTemplate;

    /**
     * 根据hash查询交易
     *
     * @param hash
     * @return
     */
    public TransactionDto getTxByHash(String hash) {
        Assert.notNull(hash, "hash can not be null.");
        String url = host + "/api/tx/" + hash;
        JSONObject result = restTemplate.getForObject(url, JSONObject.class);
        if (result.getBoolean(ResponseKey.SUCCESS)) {
            TransactionDto tx = result.getObject("data", TransactionDto.class);
            return tx;
        }
        return null;
    }
}
