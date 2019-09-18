package io.nuls.dapp.communitygovernance.mapper;

import io.nuls.dapp.communitygovernance.model.TbApplicantRecord;
import io.nuls.dapp.communitygovernance.model.TbApplicantRecordParam;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbApplicantRecordMapper {
    long countByExample(TbApplicantRecordParam example);

    int deleteByExample(TbApplicantRecordParam example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbApplicantRecord record);

    int insertSelective(TbApplicantRecord record);

    List<TbApplicantRecord> selectByExample(TbApplicantRecordParam example);

    TbApplicantRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbApplicantRecord record, @Param("example") TbApplicantRecordParam example);

    int updateByExample(@Param("record") TbApplicantRecord record, @Param("example") TbApplicantRecordParam example);

    int updateByPrimaryKeySelective(TbApplicantRecord record);

    int updateByPrimaryKey(TbApplicantRecord record);
}