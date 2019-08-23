package io.nuls.dapp.communitygovernance.mapper;

import io.nuls.dapp.communitygovernance.model.TbVoteRecord;
import io.nuls.dapp.communitygovernance.model.TbVoteRecordParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TbVoteRecordMapper {
    long countByExample(TbVoteRecordParam example);

    int deleteByExample(TbVoteRecordParam example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbVoteRecord record);

    int insertSelective(TbVoteRecord record);

    List<TbVoteRecord> selectByExample(TbVoteRecordParam example);

    TbVoteRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbVoteRecord record, @Param("example") TbVoteRecordParam example);

    int updateByExample(@Param("record") TbVoteRecord record, @Param("example") TbVoteRecordParam example);

    int updateByPrimaryKeySelective(TbVoteRecord record);

    int updateByPrimaryKey(TbVoteRecord record);
}