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
import io.nuls.dapp.communitygovernance.event.vote.VoteCreateEvent;
import io.nuls.dapp.communitygovernance.mapper.TbVoteItemMapper;
import io.nuls.dapp.communitygovernance.mapper.TbVoteMapper;
import io.nuls.dapp.communitygovernance.model.TbVote;
import io.nuls.dapp.communitygovernance.model.TbVoteItem;
import io.nuls.dapp.communitygovernance.model.contract.EventJson;
import io.nuls.dapp.communitygovernance.model.vote.VoteItem;
import io.nuls.dapp.communitygovernance.service.IEventProcessor;
import io.nuls.dapp.communitygovernance.util.TimeUtil;
import io.nuls.v2.txdata.ContractData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @author: Charlie
 * @date: 2019/8/30
 */
public class VoteCreateEventProcessor implements IEventProcessor {
    final Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private TbVoteMapper tbVoteMapper;
    @Resource
    private TbVoteItemMapper tbVoteItemMapper;

    @Value("${app.contract.address}")
    private String contractAddress;

    private static final String VOTE_CREATE_EVENT = "VoteCreateEvent";
    @Override
    public void execute(String hash, int txType, ContractData contractData, EventJson eventJson) throws Exception {
        String contractAddress = eventJson.getContractAddress();
        if(!contractAddress.equals(contractAddress)){
            return;
        }
        String event = eventJson.getEvent();
        if(!VOTE_CREATE_EVENT.equals(event)){
            return;
        }
        JSONObject payload = eventJson.getPayload();
        VoteCreateEvent voteCreateEvent = payload.toJavaObject(VoteCreateEvent.class);
        TbVote tbVote = new TbVote();
        tbVote.setContractAddress(contractAddress);
        tbVote.setContractVoteId(voteCreateEvent.getVoteId());
        tbVote.setTitle(voteCreateEvent.getTitle());
        tbVote.setDescription(voteCreateEvent.getDesc());
        tbVote.setDeposit(voteCreateEvent.getRecognizance());
        tbVote.setStatus((byte) voteCreateEvent.getStatus());
        tbVote.setCounts(0);
        tbVote.setAmount(new BigDecimal("0"));
        tbVote.setBlockHeight(eventJson.getBlockNumber());
        tbVote.setCreator(voteCreateEvent.getOwner());
        long now = TimeUtil.now();
        tbVote.setCreateTime(now);
        tbVote.setUpdateTime(now);
        tbVoteMapper.insertSelective(tbVote);
        for(VoteItem voteItem : voteCreateEvent.getItems()) {
            TbVoteItem tbVoteItem = new TbVoteItem();
            tbVoteItem.setVoteId(voteItem.getId());
            tbVoteItem.setContent(voteItem.getContent());
            tbVoteItem.setAmount(new BigDecimal("0"));
            tbVoteItem.setCounts(0);
            tbVoteItem.setCreateTime(now);
            tbVoteItem.setUpdateTime(now);
            tbVoteItemMapper.insertSelective(tbVoteItem);
        }
        logger.debug("VoteCreateEvent success height:{}", eventJson.getBlockNumber());
    }
}
