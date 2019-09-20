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

package io.nuls.dapp.communitygovernance.processor.vote;

import com.alibaba.fastjson.JSONObject;
import io.nuls.dapp.communitygovernance.constant.Constant;
import io.nuls.dapp.communitygovernance.event.vote.RedemptionVoteEvent;
import io.nuls.dapp.communitygovernance.mapper.TbVoteMapper;
import io.nuls.dapp.communitygovernance.model.TbVote;
import io.nuls.dapp.communitygovernance.model.TbVoteParam;
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
 * @date: 2019/9/20
 */
@Service
public class RedemptionVoteEventProcessor implements IEventProcessor {

    final Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private TbVoteMapper tbVoteMapper;

    @Value("${app.contract.address}")
    private String contractAddress;

    private static final String REDEMPTION_VOTE_EVENT = "RedemptionVoteEvent";

    @Override
    public void execute(String hash, int txType, ContractData contractData, EventJson eventJson) throws Exception {
        String contractAddress = eventJson.getContractAddress();
        if(!contractAddress.equals(contractAddress)){
            return;
        }
        String event = eventJson.getEvent();
        if(!REDEMPTION_VOTE_EVENT.equals(event)){
            return;
        }
        JSONObject payload = eventJson.getPayload();
        RedemptionVoteEvent redemptionVoteEvent = payload.toJavaObject(RedemptionVoteEvent.class);
        TbVoteParam tbVoteParam = new TbVoteParam();
        tbVoteParam.createCriteria().andContractVoteIdEqualTo(redemptionVoteEvent.getVoteId());
        TbVote tbVote = tbVoteMapper.selectByExample(tbVoteParam).get(0);
        tbVote.setRefund(Constant.YES);
        tbVote.setUpdateTime(TimeUtil.now());
        tbVoteMapper.updateByPrimaryKeySelective(tbVote);
        logger.debug("RedemptionVoteEvent success height:{}", eventJson.getBlockNumber());
    }
}
