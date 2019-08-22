package io.nuls.dapp.communitygovernance.mapper.vote;

import io.nuls.dapp.communitygovernance.model.vote.TbAgencyRelation;
import io.nuls.dapp.communitygovernance.model.vote.TbAgencyRelationParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbAgencyRelationMapper {
    long countByExample(TbAgencyRelationParam example);

    int deleteByExample(TbAgencyRelationParam example);

    int insert(TbAgencyRelation record);

    int insertSelective(TbAgencyRelation record);

    List<TbAgencyRelation> selectByExample(TbAgencyRelationParam example);

    int updateByExampleSelective(@Param("record") TbAgencyRelation record, @Param("example") TbAgencyRelationParam example);

    int updateByExample(@Param("record") TbAgencyRelation record, @Param("example") TbAgencyRelationParam example);
}