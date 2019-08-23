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
import io.nuls.dapp.communitygovernance.constant.Constant;
import io.nuls.dapp.communitygovernance.event.proxy.ModifyAgentEvent;
import io.nuls.dapp.communitygovernance.mapper.TbAgencyRelationMapper;
import io.nuls.dapp.communitygovernance.model.TbAgencyRelation;
import io.nuls.dapp.communitygovernance.model.TbAgencyRelationParam;
import io.nuls.dapp.communitygovernance.model.contract.EventJson;
import io.nuls.dapp.communitygovernance.service.IEventProcessor;
import io.nuls.dapp.communitygovernance.util.TimeUtil;
import io.nuls.v2.txdata.ContractData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: Charlie
 * @date: 2019/8/22
 */
@Service
public class ModifyAgentEventProcessor implements IEventProcessor {
    final Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private TbAgencyRelationMapper tbAgencyRelationMapper;

    @Value("${app.contract.address}")
    private String contractAddress;

    private static final String MODIFY_AGENT_EVENT = "ModifyAgentEvent";

    @Override
    public void execute(String hash, int txType, ContractData contractData, EventJson eventJson) throws Exception {
        String contractAddress = eventJson.getContractAddress();
        if(!contractAddress.equals(contractAddress)){
            return;
        }
        String event = eventJson.getEvent();
        if(!MODIFY_AGENT_EVENT.equals(event)){
            return;
        }
        JSONObject payload = eventJson.getPayload();
        ModifyAgentEvent modifyAgentEvent = payload.toJavaObject(ModifyAgentEvent.class);
        TbAgencyRelationParam tbAgencyRelationParam = new TbAgencyRelationParam();
        tbAgencyRelationParam.createCriteria().andMandatorEqualTo(modifyAgentEvent.getMandatorAddress()).andStatusEqualTo(Constant.VALID);
       /* List<TbAgencyRelation> tbAgencyRelations = tbAgencyRelationMapper.selectByExample(tbAgencyRelationParam);
        if(tbAgencyRelations.size() > 1){
            logger.error("数据错误, 委托者{}, 存在多个有效代理", modifyAgentEvent.getMandatorAddress());
            return;
        }else if(tbAgencyRelations.size() == 0){
            logger.error("数据错误, 委托者{}, 没有设置代理", modifyAgentEvent.getMandatorAddress());
            return;
        }
        TbAgencyRelation tbAgencyRelation = tbAgencyRelations.get(0);*/
        TbAgencyRelation tbAgencyRelation = new TbAgencyRelation();
        tbAgencyRelation.setAgent(modifyAgentEvent.getNewAgentAddress());
        tbAgencyRelation.setUpdateTime(TimeUtil.now());
        tbAgencyRelationMapper.updateByExampleSelective(tbAgencyRelation, tbAgencyRelationParam);
        logger.debug("ModifyAgentEvent success height:{}", eventJson.getBlockNumber());
    }
}
