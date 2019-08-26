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

package io.nuls.dapp.communitygovernance.processor.proposal;

import com.alibaba.fastjson.JSONObject;
import io.nuls.dapp.communitygovernance.constant.Constant;
import io.nuls.dapp.communitygovernance.event.proposal.AuditProposalEvent;
import io.nuls.dapp.communitygovernance.mapper.TbProposalAuditMapper;
import io.nuls.dapp.communitygovernance.mapper.TbProposalMapper;
import io.nuls.dapp.communitygovernance.model.TbProposal;
import io.nuls.dapp.communitygovernance.model.TbProposalAudit;
import io.nuls.dapp.communitygovernance.model.TbProposalParam;
import io.nuls.dapp.communitygovernance.model.contract.EventJson;
import io.nuls.dapp.communitygovernance.service.IEventProcessor;
import io.nuls.dapp.communitygovernance.util.TimeUtil;
import io.nuls.v2.txdata.ContractData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author: Charlie
 * @date: 2019/8/26
 */
@Service
public class AuditProposalEventProcessor implements IEventProcessor {
    final Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private TbProposalAuditMapper tbProposalAuditMapper;
    @Resource
    private TbProposalMapper tbProposalMapper;
    @Value("${app.contract.address}")
    private String contractAddress;

    private static final String AUDIT_PROPOSAL_EVENT = "AuditProposalEvent";
    @Override
    public void execute(String hash, int txType, ContractData contractData, EventJson eventJson) throws Exception {
        String contractAddress = eventJson.getContractAddress();
        if(!contractAddress.equals(contractAddress)){
            return;
        }
        String event = eventJson.getEvent();
        if(!AUDIT_PROPOSAL_EVENT.equals(event)){
            return;
        }
        JSONObject payload = eventJson.getPayload();
        AuditProposalEvent auditProposalEvent = payload.toJavaObject(AuditProposalEvent.class);
        TbProposalAudit tbProposalAudit = new TbProposalAudit();
        tbProposalAudit.setProposalId(auditProposalEvent.getId());
        tbProposalAudit.setAddress(auditProposalEvent.getAddress());
        tbProposalAudit.setStatus((byte) auditProposalEvent.getState());
        tbProposalAudit.setReason(auditProposalEvent.getReason());
        long now = TimeUtil.now();
        tbProposalAudit.setCreateTime(now);
        tbProposalAuditMapper.insert(tbProposalAudit);

        if(null != auditProposalEvent.getProposalStatus()){
            //需要修改提案状态
            TbProposalParam tbProposalParam = new TbProposalParam();
            tbProposalParam.createCriteria().andProposalIdEqualTo(auditProposalEvent.getId());
            TbProposal tbProposal = new TbProposal();
            tbProposal.setStatus(auditProposalEvent.getProposalStatus());
            tbProposal.setUpdateTime(now);
            if(auditProposalEvent.getProposalStatus() == Constant.VOTING){
                //开始投票时设置投票时间段
                tbProposal.setStartTime(new Date(auditProposalEvent.getStartTime() * 1000));
                tbProposal.setEndTime(new Date(auditProposalEvent.getEndTime() * 1000));
            }
            tbProposalMapper.updateByExampleSelective(tbProposal, tbProposalParam);
        }
        logger.debug("AuditProposalEvent success height:{}", eventJson.getBlockNumber());
    }
}
