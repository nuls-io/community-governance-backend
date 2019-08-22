package io.nuls.dapp.communitygovernance.mapper;

import io.nuls.dapp.communitygovernance.model.TbVoteItem;
import io.nuls.dapp.communitygovernance.model.TbVoteItemParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TbVoteItemMapper {
    long countByExample(TbVoteItemParam example);

    int deleteByExample(TbVoteItemParam example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbVoteItem record);

    int insertSelective(TbVoteItem record);

    List<TbVoteItem> selectByExample(TbVoteItemParam example);

    TbVoteItem selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbVoteItem record, @Param("example") TbVoteItemParam example);

    int updateByExample(@Param("record") TbVoteItem record, @Param("example") TbVoteItemParam example);

    int updateByPrimaryKeySelective(TbVoteItem record);

    int updateByPrimaryKey(TbVoteItem record);
}