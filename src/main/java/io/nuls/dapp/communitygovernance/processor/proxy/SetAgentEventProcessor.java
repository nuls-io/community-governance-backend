/**
 * MIT License
 * <p>
 * Copyright (c) 2017-2018 nuls.io
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package io.nuls.dapp.communitygovernance.processor.proxy;

import com.alibaba.fastjson.JSONObject;
import io.nuls.core.model.StringUtils;
import io.nuls.dapp.communitygovernance.constant.Constant;
import io.nuls.dapp.communitygovernance.event.proxy.SetAgentEvent;
import io.nuls.dapp.communitygovernance.mapper.TbAgencyRelationMapper;
import io.nuls.dapp.communitygovernance.mapper.TbAliasMapper;
import io.nuls.dapp.communitygovernance.model.TbAgencyRelation;
import io.nuls.dapp.communitygovernance.model.TbAlias;
import io.nuls.dapp.communitygovernance.model.TbAliasParam;
import io.nuls.dapp.communitygovernance.model.contract.EventJson;
import io.nuls.dapp.communitygovernance.service.IEventProcessor;
import io.nuls.dapp.communitygovernance.service.api.AccountServiceApi;
import io.nuls.dapp.communitygovernance.util.TimeUtil;
import io.nuls.v2.txdata.ContractData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 处理设置代理事件
 * @author: Charlie
 * @date: 2019/8/22
 */
@Service
public class SetAgentEventProcessor implements IEventProcessor {

    final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private TbAgencyRelationMapper tbAgencyRelationMapper;
    @Resource
    private TbAliasMapper tbAliasMapper;
    @Resource
    private AccountServiceApi accountServiceApi;
    @Value("${app.contract.address}")
    private String contractAddress;

    private static final String SET_AGENT_EVENT = "SetAgentEvent";

    @Override
    public void execute(String hash, int txType, ContractData contractData, EventJson eventJson) throws Exception {
        String contractAddress = eventJson.getContractAddress();
        if(!contractAddress.equals(contractAddress)){
            return;
        }
        String event = eventJson.getEvent();
        if(!SET_AGENT_EVENT.equals(event)){
            return;
        }
        JSONObject payload = eventJson.getPayload();
        SetAgentEvent setAgentEvent = payload.toJavaObject(SetAgentEvent.class);
        String agentAddress = setAgentEvent.getAgentAddress();
        String mandatorAddress = setAgentEvent.getMandatorAddress();
        TbAgencyRelation tbAgencyRelation = new TbAgencyRelation();
        tbAgencyRelation.setAgent(agentAddress);
        tbAgencyRelation.setMandator(mandatorAddress);
        tbAgencyRelation.setStatus(Constant.VALID);
        Long now = TimeUtil.now();
        tbAgencyRelation.setCreateTime(now);
        tbAgencyRelation.setUpdateTime(now);
        tbAgencyRelationMapper.insert(tbAgencyRelation);

        //记录地址别名
        TbAliasParam tbAliasParam = new TbAliasParam();
        tbAliasParam.createCriteria().andAddressEqualTo(agentAddress);
        if(tbAliasMapper.countByExample(tbAliasParam) == 0L) {
            //查别名
            String alias = accountServiceApi.getAddressAlias(agentAddress);
            if(StringUtils.isNotBlank(alias)) {
                tbAliasMapper.insert(new TbAlias(agentAddress, alias));
            }
        }
        tbAliasParam.clear();
        tbAliasParam.createCriteria().andAddressEqualTo(mandatorAddress);
        if(tbAliasMapper.countByExample(tbAliasParam) == 0L) {
            //查别名
            String alias = accountServiceApi.getAddressAlias(mandatorAddress);
            if(StringUtils.isNotBlank(alias)) {
                tbAliasMapper.insert(new TbAlias(mandatorAddress, alias));
            }
        }
        logger.debug("SetAgentEvent success height:{}", eventJson.getBlockNumber());
    }
}
