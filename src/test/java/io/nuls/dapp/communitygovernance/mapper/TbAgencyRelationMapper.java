package io.nuls.dapp.communitygovernance.mapper;

import io.nuls.dapp.communitygovernance.model.TbAgencyRelation;
import io.nuls.dapp.communitygovernance.model.TbAgencyRelationParam;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbAgencyRelationMapper {
    long countByExample(TbAgencyRelationParam example);

    int deleteByExample(TbAgencyRelationParam example);

    int insert(TbAgencyRelation record);

    int insertSelective(TbAgencyRelation record);

    List<TbAgencyRelation> selectByExample(TbAgencyRelationParam example);

    int updateByExampleSelective(@Param("record") TbAgencyRelation record, @Param("example") TbAgencyRelationParam example);

    int updateByExample(@Param("record") TbAgencyRelation record, @Param("example") TbAgencyRelationParam example);
}