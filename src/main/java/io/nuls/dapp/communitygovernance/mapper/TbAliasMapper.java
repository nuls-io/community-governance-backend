package io.nuls.dapp.communitygovernance.mapper;

import io.nuls.dapp.communitygovernance.model.TbAlias;
import io.nuls.dapp.communitygovernance.model.TbAliasParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbAliasMapper {
    long countByExample(TbAliasParam example);

    int deleteByExample(TbAliasParam example);

    int deleteByPrimaryKey(String address);

    int insert(TbAlias record);

    int insertSelective(TbAlias record);

    List<TbAlias> selectByExample(TbAliasParam example);

    TbAlias selectByPrimaryKey(String address);

    int updateByExampleSelective(@Param("record") TbAlias record, @Param("example") TbAliasParam example);

    int updateByExample(@Param("record") TbAlias record, @Param("example") TbAliasParam example);

    int updateByPrimaryKeySelective(TbAlias record);

    int updateByPrimaryKey(TbAlias record);
}