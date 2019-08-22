package io.nuls.dapp.communitygovernance.mapper.vote;

import io.nuls.dapp.communitygovernance.model.vote.TbApplicant;
import io.nuls.dapp.communitygovernance.model.vote.TbApplicantParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbApplicantMapper {
    long countByExample(TbApplicantParam example);

    int deleteByExample(TbApplicantParam example);

    int deleteByPrimaryKey(String address);

    int insert(TbApplicant record);

    int insertSelective(TbApplicant record);

    List<TbApplicant> selectByExampleWithBLOBs(TbApplicantParam example);

    List<TbApplicant> selectByExample(TbApplicantParam example);

    TbApplicant selectByPrimaryKey(String address);

    int updateByExampleSelective(@Param("record") TbApplicant record, @Param("example") TbApplicantParam example);

    int updateByExampleWithBLOBs(@Param("record") TbApplicant record, @Param("example") TbApplicantParam example);

    int updateByExample(@Param("record") TbApplicant record, @Param("example") TbApplicantParam example);

    int updateByPrimaryKeySelective(TbApplicant record);

    int updateByPrimaryKeyWithBLOBs(TbApplicant record);

    int updateByPrimaryKey(TbApplicant record);
}