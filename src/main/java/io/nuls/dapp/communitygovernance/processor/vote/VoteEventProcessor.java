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
import io.nuls.core.basic.Result;
import io.nuls.dapp.communitygovernance.config.ServerContext;
import io.nuls.dapp.communitygovernance.constant.Constant;
import io.nuls.dapp.communitygovernance.event.vote.VoteEvent;
import io.nuls.dapp.communitygovernance.mapper.TbPlayerMapper;
import io.nuls.dapp.communitygovernance.mapper.TbVoteItemMapper;
import io.nuls.dapp.communitygovernance.mapper.TbVoteMapper;
import io.nuls.dapp.communitygovernance.mapper.TbVoteRecordMapper;
import io.nuls.dapp.communitygovernance.model.*;
import io.nuls.dapp.communitygovernance.model.contract.EventJson;
import io.nuls.dapp.communitygovernance.service.IEventProcessor;
import io.nuls.dapp.communitygovernance.util.TimeUtil;
import io.nuls.v2.txdata.ContractData;
import io.nuls.v2.util.NulsSDKTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author: Charlie
 * @date: 2019/8/30
 */
public class VoteEventProcessor implements IEventProcessor {
    final Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private TbVoteMapper tbVoteMapper;
    @Resource
    private TbVoteItemMapper tbVoteItemMapper;
    @Resource
    private TbVoteRecordMapper tbVoteRecordMapper;
    @Resource
    private TbPlayerMapper tbPlayerMapper;
    @Value("${app.contract.address}")
    private String contractAddress;
    private static final String VOTE_EVENT = "VoteEvent";

    @Override
    public void execute(String hash, int txType, ContractData contractData, EventJson eventJson) throws Exception {
        String contractAddress = eventJson.getContractAddress();
        if(!contractAddress.equals(contractAddress)){
            return;
        }
        String event = eventJson.getEvent();
        if(!VOTE_EVENT.equals(event)){
            return;
        }
        /**
         * 1.获取vote的记录,判断投票是否在进行中
         * 2.查询该用户是否已经投过票
         * 3.1 如果投过票(表示修改投票)
         *      1) 作废之前的投票记录(可能包含多条),
         *      2) vote总得票数中依次减去每一条得票数,
         *      3) 从对应选项记录总得票数中依次减去每一条得票数
         * 3.2 1) 新增新的投票记录(可能包含多条),
         *     2) vote总得票数中依次加上每一条得票数,
         *     3) 从对应选项记录总得票数中依次加上每一条得票数
         *
         * 2.1 如果没有投过票, 新增投票记录, 增加vote总得票数, 总投票人数量
         */
        JSONObject payload = eventJson.getPayload();
        VoteEvent voteEvent = payload.toJavaObject(VoteEvent.class);
        long voteId = voteEvent.getVoteId();

        TbVoteParam tbVoteParam = new TbVoteParam();
        tbVoteParam.createCriteria().andContractVoteIdEqualTo(voteId);
        TbVote tbVote = tbVoteMapper.selectByExample(tbVoteParam).get(0);
        if(tbVote.getStatus() != Constant.STATUS_VOTEING){
            logger.error("Not voting,  vote status is {}", tbVote.getStatus());
            return;
        }
        TbVoteRecordParam tbVoteRecordParam = new TbVoteRecordParam();
        tbVoteRecordParam.createCriteria()
                .andVoteIdEqualTo(voteId)
                .andVoterEqualTo(voteEvent.getVoterAddress())
                .andCancelTypeEqualTo(Constant.NO);
        //查询历史投票
        List<TbVoteRecord> recordList = tbVoteRecordMapper.selectByExample(tbVoteRecordParam);
        long now = TimeUtil.now();
        //获取投票者当前持有的票数
        String voterAddress = voteEvent.getVoterAddress();
        Result rs = NulsSDKTool.getAccountBalance(voterAddress, ServerContext.chainId, ServerContext.assetId);
        Map map = (Map) rs.getData();
        //获取余额对应投票数
        BigDecimal number = new BigDecimal((String) map.get("totalBalance"));
        //本次投票包含的 之前已经投过的选项(不用作废记录,和新增投票记录)
        List<Long> voteditemIds = new ArrayList<>();
        if(!recordList.isEmpty()){
            for(TbVoteRecord tbVoteRecord : recordList){
                if(voteEvent.getItemIds().contains(tbVoteRecord.getItemId())){
                    //新的投票选项,同时存在于旧的投票记录中时, 不用作废.
                    voteditemIds.add(tbVoteRecord.getItemId());
                    continue;
                }

                //依次减去投票选项表的统计
                TbVoteItemParam tbVoteItemParam = new TbVoteItemParam();
                tbVoteItemParam.createCriteria().andVoteIdEqualTo(voteId).andItemIdEqualTo(tbVoteRecord.getItemId());
                TbVoteItem tbVoteItem = tbVoteItemMapper.selectByExample(tbVoteItemParam).get(0);
                tbVoteItem.setCounts(tbVoteItem.getCounts() - 1);
                tbVoteItem.setAmount(tbVoteItem.getAmount().subtract(number));
                tbVoteItem.setUpdateTime(now);
                tbVoteItemMapper.updateByPrimaryKeySelective(tbVoteItem);

                //作废之前的记录
                TbVoteRecordParam recordParam = new TbVoteRecordParam();
                recordParam.createCriteria()
                        .andVoteIdEqualTo(voteId)
                        .andItemIdEqualTo(tbVoteRecord.getItemId())
                        .andVoterEqualTo(voteEvent.getVoterAddress())
                        .andCancelTypeEqualTo(Constant.NO);
                TbVoteRecord record = new TbVoteRecord();
                record.setCancelType(Constant.YES);
                tbVoteRecordMapper.updateByExampleSelective(record, recordParam);
                //依次减去vote表的统计
                tbVote.setAmount(tbVote.getAmount().subtract(number));
            }
        }

        for(long itemId : voteEvent.getItemIds()){
            if(voteditemIds.contains(itemId)){
                //本次投票包含的 之前已经投过的选项(不用新增投票记录)
                continue;
            }
            TbVoteRecord tbVoteRecord = new TbVoteRecord();
            tbVoteRecord.setVoteId(voteId);
            tbVoteRecord.setItemId(itemId);
            tbVoteRecord.setVoter(voteEvent.getVoterAddress());
            tbVoteRecord.setAmount(number);
            tbVoteRecord.setCancelType(Constant.NO);
            tbVoteRecord.setCreateTime(now);
            tbVoteRecord.setUpdateTime(now);
            tbVoteRecordMapper.insertSelective(tbVoteRecord);

            TbVoteItemParam tbVoteItemParam = new TbVoteItemParam();
            tbVoteItemParam.createCriteria().andVoteIdEqualTo(voteId).andItemIdEqualTo(itemId);
            TbVoteItem tbVoteItem = tbVoteItemMapper.selectByExample(tbVoteItemParam).get(0);
            tbVoteItem.setCounts(tbVoteItem.getCounts() + 1);
            tbVoteItem.setAmount(tbVoteItem.getAmount().add(number));
            tbVoteItem.setUpdateTime(now);
            tbVoteItemMapper.updateByPrimaryKeySelective(tbVoteItem);
        }
        tbVote.setCounts(tbVote.getCounts() + 1);
        tbVote.setAmount(tbVote.getAmount().add(number));
        tbVote.setUpdateTime(now);

        tbVoteMapper.updateByPrimaryKeySelective(tbVote);

        //记录新投票参与者
        TbPlayerParam tbPlayerParam = new TbPlayerParam();
        tbPlayerParam.createCriteria().andAddressEqualTo(voteEvent.getVoterAddress());
        if(tbPlayerMapper.countByExample(tbPlayerParam) == 0L){
            tbPlayerMapper.insert(new TbPlayer(voteEvent.getVoterAddress()));
        }
        logger.debug("VoteEvent success height:{}", eventJson.getBlockNumber());
    }
}
