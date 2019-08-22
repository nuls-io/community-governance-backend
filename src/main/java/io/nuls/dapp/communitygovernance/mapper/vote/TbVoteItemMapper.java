package io.nuls.dapp.communitygovernance.mapper.vote;

import io.nuls.dapp.communitygovernance.model.vote.TbVoteItem;
import io.nuls.dapp.communitygovernance.model.vote.TbVoteItemParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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