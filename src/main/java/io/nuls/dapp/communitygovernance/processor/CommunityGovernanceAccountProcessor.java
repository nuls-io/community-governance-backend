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
import io.nuls.dapp.communitygovernance.config.ServerContext;
import io.nuls.dapp.communitygovernance.constant.Constant;
import io.nuls.dapp.communitygovernance.mapper.*;
import io.nuls.dapp.communitygovernance.model.*;
import io.nuls.dapp.communitygovernance.service.IAccountBalanceProcessor;
import io.nuls.dapp.communitygovernance.util.AppUtil;
import io.nuls.dapp.communitygovernance.util.TimeUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
                if(!playerSet.contains(address)){
                    //不是参与者的地址, 无需处理
                   continue;
                }
                BigDecimal amount = AppUtil.toNuls(coinFrom.getAmount());
                long now = TimeUtil.now();
                //理事会
                tbApplicantMapper.updateAmountByVoter(address, amount);
                TbApplicantRecord tbApplicantRecord = new TbApplicantRecord();
                tbApplicantRecord.setAmount(amount);
                tbApplicantRecord.setUpdateTime(now);
                TbApplicantRecordParam tbApplicantRecordParam = new TbApplicantRecordParam();
                tbApplicantRecordParam.createCriteria().andVoterEqualTo(address).andStatusEqualTo(Constant.YES);
                tbApplicantRecordMapper.updateByExampleSelective(tbApplicantRecord, tbApplicantRecordParam);

                //提案
                TbProposalVoteRecordParam tbProposalVoteRecordParam = new TbProposalVoteRecordParam();
                tbProposalVoteRecordParam.createCriteria().andVoterEqualTo(address);
                List<TbProposalVoteRecord> tbProposalVoteRecordList = tbProposalVoteRecordMapper.selectByExample(tbProposalVoteRecordParam);
                for(TbProposalVoteRecord tbProposalVoteRecord : tbProposalVoteRecordList){
                    TbProposal tbProposal = tbProposalMapper.selectByPrimaryKey(tbProposalVoteRecord.getProposalId());
                    switch (tbProposalVoteRecord.getResult()){
                        case Constant.FAVOUR:
                            tbProposal.setFavour(tbProposal.getFavour().subtract(amount));
                        case Constant.AGAINST:
                            tbProposal.setAgainst(tbProposal.getAgainst().subtract(amount));
                        case Constant.ABSTENTION:
                            tbProposal.setAbstention(tbProposal.getAbstention().subtract(amount));
                    }
                    tbProposal.setUpdateTime(now);
                    tbProposalMapper.updateByPrimaryKeySelective(tbProposal);
                }
                TbProposalVoteRecord tbProposalVoteRecord = new TbProposalVoteRecord();
                tbProposalVoteRecord.setAmount(amount);
                tbProposalVoteRecord.setUpdateTime(now);
                tbProposalVoteRecordMapper.updateByExampleSelective(tbProposalVoteRecord, tbProposalVoteRecordParam);

                //普通投票
                TbVoteRecord tbVoteRecord = new TbVoteRecord();
                tbVoteRecord.setAmount(amount);
                tbVoteRecord.setUpdateTime(now);
                TbVoteRecordParam tbVoteRecordParam = new TbVoteRecordParam();
                tbVoteRecordParam.createCriteria().andVoterEqualTo(address).andCancelTypeEqualTo(Constant.YES);

            }
        }
        if (!nonCoinTo) {
            //加金额
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
