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
package io.nuls.dapp.communitygovernance.processor;

import io.nuls.base.basic.AddressTool;
import io.nuls.base.data.CoinData;
import io.nuls.base.data.CoinFrom;
import io.nuls.base.data.CoinTo;
import io.nuls.dapp.communitygovernance.config.ServerContext;
import io.nuls.dapp.communitygovernance.constant.Constant;
import io.nuls.dapp.communitygovernance.mapper.*;
import io.nuls.dapp.communitygovernance.model.*;
import io.nuls.dapp.communitygovernance.model.proposal.ProposalAmountPO;
import io.nuls.dapp.communitygovernance.service.IAccountBalanceProcessor;
import io.nuls.dapp.communitygovernance.util.AppUtil;
import io.nuls.dapp.communitygovernance.util.TimeUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * 处理每个区块中交易, 涉及到社区治理投票参与者的余额变化
 *
 * @author: PierreLuo
 * @date: 2019-08-12
 */
@Service
public class CommunityGovernanceAccountProcessor implements IAccountBalanceProcessor {
    @Resource
    private TbPlayerMapper tbPlayerMapper;
    @Resource
    private TbApplicantMapper tbApplicantMapper;
    @Resource
    private TbApplicantRecordMapper tbApplicantRecordMapper;
    @Resource
    private TbProposalMapper tbProposalMapper;
    @Resource
    private TbProposalVoteRecordMapper tbProposalVoteRecordMapper;
    @Resource
    private TbVoteMapper tbVoteMapper;
    @Resource
    private TbVoteItemMapper tbVoteItemMapper;
    @Resource
    private TbVoteRecordMapper tbVoteRecordMapper;

    @Override
    public void execute(int txType, CoinData coinData) {
        if (null == coinData) {
            return;
        }
        boolean nonCoinFrom = null == coinData.getFrom() || coinData.getFrom().isEmpty();
        boolean nonCoinTo = null == coinData.getTo() || coinData.getTo().isEmpty();
        if (nonCoinFrom && nonCoinTo) {
            return;
        }
        Set<String> playerSet = getPlayers();
        if (playerSet.isEmpty()) {
            return;
        }
        if (!nonCoinFrom) {
            //扣金额
            for (CoinFrom coinFrom : coinData.getFrom()) {
                if (coinFrom.getAssetsChainId() != ServerContext.chainId && coinFrom.getAssetsId() != ServerContext.assetId) {
                    //资产不是NULS, 无需处理
                    continue;
                }
                byte[] addrBytes = coinFrom.getAddress();
                String address = AddressTool.getStringAddressByBytes(addrBytes);
                if (!playerSet.contains(address)) {
                    //不是参与者的地址, 无需处理
                    continue;
                }
                BigDecimal amount = AppUtil.toNuls(coinFrom.getAmount());
                long now = TimeUtil.now();

                //理事会
                tbApplicantMapper.updateSubtractAmountByVoter(address, amount, now);
                //提案
                processProposal(false, address, amount, now);
                //普通投票
                processVote(false, address, amount, now);
            }
        }
        if (!nonCoinTo) {
            //加金额
            for (CoinTo coinTo : coinData.getTo()) {
                if (coinTo.getAssetsChainId() != ServerContext.chainId && coinTo.getAssetsId() != ServerContext.assetId) {
                    //资产不是NULS, 无需处理
                    continue;
                }
                byte[] addrBytes = coinTo.getAddress();
                String address = AddressTool.getStringAddressByBytes(addrBytes);
                if (!playerSet.contains(address)) {
                    //不是参与者的地址, 无需处理
                    continue;
                }
                BigDecimal amount = AppUtil.toNuls(coinTo.getAmount());
                long now = TimeUtil.now();
                //理事会
                tbApplicantMapper.updateSubtractAmountByVoter(address, amount, now);
                //提案
                processProposal(true, address, amount, now);
                //普通投票
                processVote(true, address, amount, now);

            }
        }

    }

    /**
     * 处理提案票数更新
     *
     * @param add     true  处理coinTo 加票, false 处理coinFrom 减票
     * @param address
     * @param amount
     * @param now
     */
    private void processProposal(boolean add, String address, BigDecimal amount, long now) {
        List<ProposalAmountPO> proposalAmountPOList = tbProposalMapper.selectVotingByVoter(address);
        for (ProposalAmountPO proposalAmountPO : proposalAmountPOList) {
            TbProposal tbProposal = new TbProposal();
            tbProposal.setProposalId(proposalAmountPO.getProposalId());
            switch (proposalAmountPO.getResult()) {
                case Constant.FAVOUR:
                    BigDecimal favour = add ? proposalAmountPO.getFavour().add(amount) : proposalAmountPO.getFavour().subtract(amount);
                    tbProposal.setFavour(favour);
                case Constant.AGAINST:
                    BigDecimal against = add ? proposalAmountPO.getAgainst().add(amount) : proposalAmountPO.getAgainst().subtract(amount);
                    tbProposal.setAgainst(against);
                case Constant.ABSTENTION:
                    BigDecimal abstention = add ? proposalAmountPO.getAbstention().add(amount) : proposalAmountPO.getAbstention().subtract(amount);
                    tbProposal.setAbstention(abstention);
            }
            tbProposal.setUpdateTime(now);
            tbProposalMapper.updateByPrimaryKeySelective(tbProposal);

            TbProposalVoteRecord tbProposalVoteRecord = new TbProposalVoteRecord();
            tbProposalVoteRecord.setId(proposalAmountPO.getRecordId());
            BigDecimal value = add ? proposalAmountPO.getAmount().add(amount) : proposalAmountPO.getAmount().subtract(amount);
            tbProposalVoteRecord.setAmount(value);
            tbProposalVoteRecord.setUpdateTime(now);
            tbProposalVoteRecordMapper.updateByPrimaryKeySelective(tbProposalVoteRecord);
        }
    }


    /**
     * 处理普通投票
     *
     * @param add
     * @param address
     * @param amount
     * @param now
     */
    private void processVote(boolean add, String address, BigDecimal amount, long now) {
        int updateCount = 0;
        if (add) {
            updateCount = tbVoteItemMapper.updateAddAmountByVoter(address, amount, now);
        } else {
            updateCount = tbVoteItemMapper.updateSubtractAmountByVoter(address, amount, now);
        }
        if (updateCount == 0) {
            return;
        }
        TbVoteRecordParam tbVoteRecordParam = new TbVoteRecordParam();
        tbVoteRecordParam.createCriteria().andVoterEqualTo(address).andCancelTypeEqualTo(Constant.YES);
        List<TbVoteRecord> tbVoteRecordList = tbVoteRecordMapper.selectByExample(tbVoteRecordParam);
        //记录一个vote, 该地址投了几个选项,累计当前票数, 然后再vote一并扣除
        Map<Long, BigDecimal> map = new HashMap<>();
        for (TbVoteRecord tbVoteRecord : tbVoteRecordList) {
            if (map.containsKey(tbVoteRecord.getVoteId())) {
                BigDecimal total = map.get(tbVoteRecord.getVoteId());
                map.put(tbVoteRecord.getVoteId(), total.add(amount));
            } else {
                map.put(tbVoteRecord.getVoteId(), amount);
            }
        }
        TbVoteParam tbVoteParam = new TbVoteParam();
        for (Map.Entry<Long, BigDecimal> entry : map.entrySet()) {
            tbVoteParam.clear();
            tbVoteParam.createCriteria().andContractVoteIdEqualTo(entry.getKey()).andStatusEqualTo(Constant.VOTING);
            TbVote tbVote = tbVoteMapper.selectByExample(tbVoteParam).get(0);
            BigDecimal value = add ? tbVote.getAmount().add(entry.getValue()) : tbVote.getAmount().subtract(entry.getValue());
            tbVote.setAmount(value);
            tbVote.setUpdateTime(now);
            tbVoteMapper.updateByPrimaryKeySelective(tbVote);
        }
    }

    /**
     * 获取社区治理所有投票参与者
     *
     * @return
     */
    private Set<String> getPlayers() {
        Set<String> playerSet = new HashSet<>();
        List<TbPlayer> playerList = tbPlayerMapper.selectByExample(new TbPlayerParam());

        if (null == playerList || playerList.isEmpty()) {
            return playerSet;
        }
        for (TbPlayer tbPlayer : playerList) {
            playerSet.add(tbPlayer.getAddress());
        }
        return playerSet;
    }
}
