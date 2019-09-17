package io.nuls.dapp.communitygovernance.mapper;

import io.nuls.dapp.communitygovernance.model.TbPlayer;
import io.nuls.dapp.communitygovernance.model.TbPlayerParam;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbPlayerMapper {
    long countByExample(TbPlayerParam example);

    int deleteByExample(TbPlayerParam example);

    int insert(TbPlayer record);

    int insertSelective(TbPlayer record);

    List<TbPlayer> selectByExample(TbPlayerParam example);

    int updateByExampleSelective(@Param("record") TbPlayer record, @Param("example") TbPlayerParam example);

    int updateByExample(@Param("record") TbPlayer record, @Param("example") TbPlayerParam example);
}