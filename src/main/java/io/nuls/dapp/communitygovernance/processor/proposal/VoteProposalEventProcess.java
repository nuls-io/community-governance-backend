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
import io.nuls.core.basic.Result;
import io.nuls.dapp.communitygovernance.config.ServerContext;
import io.nuls.dapp.communitygovernance.constant.Constant;
import io.nuls.dapp.communitygovernance.event.proposal.VoteProposalEvent;
import io.nuls.dapp.communitygovernance.mapper.TbProposalMapper;
import io.nuls.dapp.communitygovernance.mapper.TbProposalVoteRecordMapper;
import io.nuls.dapp.communitygovernance.model.TbProposal;
import io.nuls.dapp.communitygovernance.model.TbProposalParam;
import io.nuls.dapp.communitygovernance.model.TbProposalVoteRecord;
import io.nuls.dapp.communitygovernance.model.contract.EventJson;
import io.nuls.dapp.communitygovernance.service.IEventProcessor;
import io.nuls.dapp.communitygovernance.util.TimeUtil;
import io.nuls.v2.txdata.ContractData;
import io.nuls.v2.util.NulsSDKTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Map;

/**
 * @author: Charlie
 * @date: 2019/8/26
 */
@Service
public class VoteProposalEventProcess implements IEventProcessor {
    final Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private TbProposalMapper tbProposalMapper;
    @Resource
    private TbProposalVoteRecordMapper tbProposalVoteRecordMapper;
    @Value("${app.contract.address}")
    private String contractAddress;

    private static final String VOTE_PROPOSAL_EVENT = "VoteProposalEvent";

    @Override
    public void execute(String hash, int txType, ContractData contractData, EventJson eventJson) throws Exception {
        String contractAddress = eventJson.getContractAddress();
        if (!contractAddress.equals(contractAddress)) {
            return;
        }
        String event = eventJson.getEvent();
        if (!VOTE_PROPOSAL_EVENT.equals(event)) {
            return;
        }
        JSONObject payload = eventJson.getPayload();
        VoteProposalEvent voteProposalEvent = payload.toJavaObject(VoteProposalEvent.class);

        String voterAddress = voteProposalEvent.getAddress();
        Result rs = NulsSDKTool.getAccountBalance(voterAddress, ServerContext.chainId, ServerContext.assetId);
        Map map = (Map) rs.getData();
        //获取余额对应投票数
        BigDecimal number = new BigDecimal((String) map.get("totalBalance"));
        //新增记录
        TbProposalVoteRecord tbProposalVoteRecord = new TbProposalVoteRecord();
        tbProposalVoteRecord.setProposalId(voteProposalEvent.getId());
        tbProposalVoteRecord.setVoter(voteProposalEvent.getAddress());
        tbProposalVoteRecord.setResult((byte) voteProposalEvent.getResult());
        tbProposalVoteRecord.setVotes(number);
        long now = TimeUtil.now();
        tbProposalVoteRecord.setCreateTime(now);
        tbProposalVoteRecord.setUpdateTime(now);
        tbProposalVoteRecordMapper.insert(tbProposalVoteRecord);

        //修改对应提案的票数
        TbProposalParam tbProposalParam = new TbProposalParam();
        tbProposalParam.createCriteria().andProposalIdEqualTo(voteProposalEvent.getId());
        TbProposal proposal = tbProposalMapper.selectByExample(tbProposalParam).get(0);

        TbProposal tbProposal = new TbProposal();
        tbProposal.setCounts(proposal.getCounts() + 1);
        int result = voteProposalEvent.getResult();
        if(result == Constant.FAVOUR){
            tbProposal.setFavour(proposal.getFavour().add(number));
        }else if(result == Constant.AGAINST){
            tbProposal.setAgainst(proposal.getAgainst().add(number));
        }else if(result == Constant.ABSTENTION){
            tbProposal.setAbstention(proposal.getAbstention().add(number));
        }
        tbProposal.setUpdateTime(TimeUtil.now());
        tbProposalMapper.updateByExampleSelective(tbProposal, tbProposalParam);
        logger.debug("VoteProposalEvent success height:{}", eventJson.getBlockNumber());
    }
}
