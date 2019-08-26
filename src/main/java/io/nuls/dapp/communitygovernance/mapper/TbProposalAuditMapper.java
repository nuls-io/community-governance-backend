package io.nuls.dapp.communitygovernance.mapper;

import io.nuls.dapp.communitygovernance.model.TbProposalAudit;
import io.nuls.dapp.communitygovernance.model.TbProposalAuditParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbProposalAuditMapper {
    long countByExample(TbProposalAuditParam example);

    int deleteByExample(TbProposalAuditParam example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbProposalAudit record);

    int insertSelective(TbProposalAudit record);

    List<TbProposalAudit> selectByExample(TbProposalAuditParam example);

    TbProposalAudit selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbProposalAudit record, @Param("example") TbProposalAuditParam example);

    int updateByExample(@Param("record") TbProposalAudit record, @Param("example") TbProposalAuditParam example);

    int updateByPrimaryKeySelective(TbProposalAudit record);

    int updateByPrimaryKey(TbProposalAudit record);
}