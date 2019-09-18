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

package io.nuls.dapp.communitygovernance.processor.council;

import com.alibaba.fastjson.JSONObject;
import io.nuls.core.basic.Result;
import io.nuls.dapp.communitygovernance.config.ServerContext;
import io.nuls.dapp.communitygovernance.constant.Constant;
import io.nuls.dapp.communitygovernance.event.council.VoteDirectorEvent;
import io.nuls.dapp.communitygovernance.mapper.TbApplicantMapper;
import io.nuls.dapp.communitygovernance.mapper.TbApplicantRecordMapper;
import io.nuls.dapp.communitygovernance.mapper.TbPlayerMapper;
import io.nuls.dapp.communitygovernance.model.*;
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
import java.util.*;

/**
 * @author: Charlie
 * @date: 2019/8/23
 */
@Service
public class VoteDirectorEventProcessor implements IEventProcessor {
    final Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private TbApplicantMapper tbApplicantMapper;
    @Resource
    private TbApplicantRecordMapper tbApplicantRecordMapper;
    @Resource
    private TbPlayerMapper tbPlayerMapper;
    @Value("${app.contract.address}")
    private String contractAddress;

    private static final String VOTE_DIRECTOR_EVENT = "VoteDirectorEvent";

    @Override
    public void execute(String hash, int txType, ContractData contractData, EventJson eventJson) throws Exception {
        String contractAddress = eventJson.getContractAddress();
        if(!contractAddress.equals(contractAddress)){
            return;
        }
        String event = eventJson.getEvent();
        if(!VOTE_DIRECTOR_EVENT.equals(event)){
            return;
        }
        JSONObject payload = eventJson.getPayload();
        VoteDirectorEvent voteDirectorEvent = payload.toJavaObject(VoteDirectorEvent.class);
        /**
         * 1.获取投票者余额
         * 2.新增投票记录
         * 3.获取被取消的被投票者
         * 4.修改被取消的被投票申请人的总得票数
         * 5.修改新的被投票申请人的总得票数
         */
        String voterAddress = voteDirectorEvent.getVoterAddress();
        Result rs = NulsSDKTool.getAccountBalance(voterAddress, ServerContext.chainId, ServerContext.assetId);
        Map map = (Map) rs.getData();
        BigDecimal number = new BigDecimal((String)map.get("totalBalance"));

        //获取该投票者上一次的有效投票记录
        TbApplicantRecordParam tbApplicantRecordParam = new TbApplicantRecordParam();
        tbApplicantRecordParam.createCriteria().andVoterEqualTo(voteDirectorEvent.getVoterAddress()).andStatusEqualTo(Constant.VALID);
        List<TbApplicantRecord> tbApplicantRecords = tbApplicantRecordMapper.selectByExample(tbApplicantRecordParam);
        if(!tbApplicantRecords.isEmpty()) {
            //统计本次投票后，失效的投票记录
            List<String> delList = new ArrayList<>();
            for (TbApplicantRecord tbApplicantRecord : tbApplicantRecords) {
                boolean exist = false;
                for (String applicant : voteDirectorEvent.getApplicantAddress()) {
                    if (applicant.equals(tbApplicantRecord.getApplicant())) {
                        exist = true;
                        break;
                    }
                }
                if (!exist) {
                    //记录需要失效的id,该申请者本次没有被投票者投票,需要解除之前的投票记录
                    delList.add(tbApplicantRecord.getApplicant());
                }
            }

            //从投票记录中记为失效
            TbApplicantRecordParam arParam = new TbApplicantRecordParam();
            arParam.createCriteria().andApplicantIn(delList).andStatusEqualTo(Constant.VALID);
            TbApplicantRecord ar = new TbApplicantRecord();
            ar.setStatus(Constant.INVALID);
            ar.setUpdateTime(TimeUtil.now());
            tbApplicantRecordMapper.updateByExampleSelective(ar, arParam);

            //从申请者总票数中减去对应的票数
            TbApplicantParam tbApplicantParam = new TbApplicantParam();
            tbApplicantParam.createCriteria().andAddressIn(delList).andStatusEqualTo(Constant.VALID);
            List<TbApplicant> tbApplicantDecreaseList = tbApplicantMapper.selectByExample(tbApplicantParam);
            for (TbApplicant tbApplicant : tbApplicantDecreaseList) {
                BigDecimal current = tbApplicant.getAmount().subtract(number);
                BigDecimal surplus = current.compareTo(BigDecimal.ZERO) > 0 ? current : BigDecimal.ZERO;
                tbApplicant.setAmount(surplus);
                tbApplicant.setCounts(tbApplicant.getCounts() - 1);
                tbApplicant.setUpdateTime(TimeUtil.now());
                tbApplicantMapper.updateByExampleSelective(tbApplicant, tbApplicantParam);
            }
        }

        //统计新增的投票记录
        Set<String> newSet = new HashSet<>();
        for(String applicant : voteDirectorEvent.getApplicantAddress()){
            boolean exist = false;
            for(TbApplicantRecord tbApplicantRecord : tbApplicantRecords){
                if(applicant.equals(tbApplicantRecord.getApplicant())){
                    exist = true;
                    break;
                }
            }
            if(!exist){
                //记录新增的被投票人
                newSet.add(applicant);
                TbApplicantRecord record = new TbApplicantRecord();
                long now = TimeUtil.now();
                record.setStatus(Constant.VALID);
                record.setCreateTime(now);
                record.setAmount(number);
                record.setUpdateTime(now);
                tbApplicantRecordMapper.insert(record);
            }
        }

        //为本次所有被投票者统计最新的票数
        TbApplicantParam tbApplicantParamNew = new TbApplicantParam();
        tbApplicantParamNew.createCriteria().andAddressIn(Arrays.asList(voteDirectorEvent.getApplicantAddress())).andStatusEqualTo(Constant.VALID);
        List<TbApplicant> tbApplicantNewList = tbApplicantMapper.selectByExample(tbApplicantParamNew);
        for(TbApplicant tbApplicant : tbApplicantNewList){
            //为被投票者更新最新票数
            tbApplicant.setAmount(tbApplicant.getAmount().add(number));
            tbApplicant.setUpdateTime(TimeUtil.now());
            //如果是投票者本次新增的被投票者，则为被投票者计算投票人总数
            if(!newSet.contains(tbApplicant.getAddress())){
                tbApplicant.setCounts(tbApplicant.getCounts() + 1);
            }
            tbApplicantMapper.updateByExampleSelective(tbApplicant, tbApplicantParamNew);
        }

        //记录新投票参与者
        TbPlayerParam tbPlayerParam = new TbPlayerParam();
        tbPlayerParam.createCriteria().andAddressEqualTo(voterAddress);
        if(tbPlayerMapper.countByExample(tbPlayerParam) == 0L){
            tbPlayerMapper.insert(new TbPlayer(voterAddress));
        }

        logger.debug("VoteDirectorEvent success height:{}", eventJson.getBlockNumber());
    }
}
