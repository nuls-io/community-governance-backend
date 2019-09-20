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

package io.nuls.dapp.communitygovernance.scheduled;

import io.nuls.dapp.communitygovernance.constant.Constant;
import io.nuls.dapp.communitygovernance.mapper.TbProposalMapper;
import io.nuls.dapp.communitygovernance.mapper.TbVoteMapper;
import io.nuls.dapp.communitygovernance.model.TbProposal;
import io.nuls.dapp.communitygovernance.model.TbProposalParam;
import io.nuls.dapp.communitygovernance.model.TbVote;
import io.nuls.dapp.communitygovernance.model.TbVoteParam;
import io.nuls.dapp.communitygovernance.util.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

/**
 * @author: Charlie
 * @date: 2019/9/20
 */
@Component
public class StatusScheduled {

    final Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private TbProposalMapper tbProposalMapper;
    @Resource
    private TbVoteMapper tbVoteMapper;

    @Scheduled(cron = "0 0/1 * * * ?")
    public void scanTimeUpdateStatus() {
        logger.info("执行扫描各项投票是否处于投票时段:{}", System.currentTimeMillis());
        //提案
        scanProposal();
        //普通投票
        scanVote();
    }

    /**
     * 处理提案
     */
    private void scanProposal() {
        TbProposalParam tbProposalParam = new TbProposalParam();
        tbProposalParam.createCriteria().andStatusEqualTo(Constant.VOTING);
        List<TbProposal> tbProposalList = tbProposalMapper.selectByExample(tbProposalParam);
        Date now = new Date();
        for (TbProposal tbProposal : tbProposalList) {
            if (now.getTime() > tbProposal.getEndTime().getTime()) {
                //超过了投票时间,需要修改提案状态
                TbProposal tbProposalUpdate = new TbProposal();
                tbProposalUpdate.setProposalId(tbProposal.getProposalId());
                //判断提案是否通过
                BigDecimal total = tbProposal.getFavour().add(tbProposal.getAgainst()).add(tbProposal.getAbstention());
                BigDecimal favourRate = tbProposal.getFavour().divide(total, 4, RoundingMode.DOWN);
                if(favourRate.compareTo(Constant.ADOPTED_THRESHOLD) >= 0){
                    tbProposalUpdate.setStatus(Constant.ADOPTED);
                }else{
                    tbProposalUpdate.setStatus(Constant.REJECTED);
                }
                tbProposalUpdate.setUpdateTime(TimeUtil.now());
                tbProposalMapper.updateByPrimaryKeySelective(tbProposalUpdate);
            }
        }
    }

    /**
     * 处理普通投票
     */
    private void scanVote() {
        TbVoteParam tbVoteParam = new TbVoteParam();
        tbVoteParam.createCriteria().andStatusEqualTo(Constant.STATUS_VOTEING);
        List<TbVote> tbVoteList = tbVoteMapper.selectByExample(tbVoteParam);
        Date now = new Date();
        for (TbVote tbVote : tbVoteList) {
            if(now.getTime() > tbVote.getEndTime().getTime()){
                TbVote tbVoteUpdate = new TbVote();
                tbVoteUpdate.setId(tbVote.getId());
                tbVoteUpdate.setStatus(Constant.STATUS_CLOSE);
                tbVoteUpdate.setUpdateTime(TimeUtil.now());
                tbVoteMapper.updateByPrimaryKeySelective(tbVote);
            }
        }
    }
}
