package io.nuls.dapp.communitygovernance.mapper;

import io.nuls.dapp.communitygovernance.model.TbProposalVoteRecord;
import io.nuls.dapp.communitygovernance.model.TbProposalVoteRecordParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbProposalVoteRecordMapper {
    long countByExample(TbProposalVoteRecordParam example);

    int deleteByExample(TbProposalVoteRecordParam example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbProposalVoteRecord record);

    int insertSelective(TbProposalVoteRecord record);

    List<TbProposalVoteRecord> selectByExample(TbProposalVoteRecordParam example);

    TbProposalVoteRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbProposalVoteRecord record, @Param("example") TbProposalVoteRecordParam example);

    int updateByExample(@Param("record") TbProposalVoteRecord record, @Param("example") TbProposalVoteRecordParam example);

    int updateByPrimaryKeySelective(TbProposalVoteRecord record);

    int updateByPrimaryKey(TbProposalVoteRecord record);
}