package io.nuls.dapp.communitygovernance.mapper;

import io.nuls.dapp.communitygovernance.model.TbVote;
import io.nuls.dapp.communitygovernance.model.TbVoteParam;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbVoteMapper {
    long countByExample(TbVoteParam example);

    int deleteByExample(TbVoteParam example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbVote record);

    int insertSelective(TbVote record);

    List<TbVote> selectByExample(TbVoteParam example);

    TbVote selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbVote record, @Param("example") TbVoteParam example);

    int updateByExample(@Param("record") TbVote record, @Param("example") TbVoteParam example);

    int updateByPrimaryKeySelective(TbVote record);

    int updateByPrimaryKey(TbVote record);
}