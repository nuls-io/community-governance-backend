package io.nuls.dapp.communitygovernance.mapper;

import io.nuls.dapp.communitygovernance.model.TbProposal;
import io.nuls.dapp.communitygovernance.model.TbProposalParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbProposalMapper {
    long countByExample(TbProposalParam example);

    int deleteByExample(TbProposalParam example);

    int deleteByPrimaryKey(Integer proposalId);

    int insert(TbProposal record);

    int insertSelective(TbProposal record);

    List<TbProposal> selectByExampleWithBLOBs(TbProposalParam example);

    List<TbProposal> selectByExample(TbProposalParam example);

    TbProposal selectByPrimaryKey(Integer proposalId);

    int updateByExampleSelective(@Param("record") TbProposal record, @Param("example") TbProposalParam example);

    int updateByExampleWithBLOBs(@Param("record") TbProposal record, @Param("example") TbProposalParam example);

    int updateByExample(@Param("record") TbProposal record, @Param("example") TbProposalParam example);

    int updateByPrimaryKeySelective(TbProposal record);

    int updateByPrimaryKeyWithBLOBs(TbProposal record);

    int updateByPrimaryKey(TbProposal record);
}