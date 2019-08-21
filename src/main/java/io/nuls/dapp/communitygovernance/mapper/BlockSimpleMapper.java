package io.nuls.dapp.communitygovernance.mapper;

import io.nuls.dapp.communitygovernance.model.SimpleBlockHeader;
import org.apache.ibatis.annotations.Mapper;

/**
 * 区块高度本地保存
 * Created by wangkun23 on 2018/8/30.
 */
@Mapper
public interface BlockSimpleMapper {

    /**
     * 高度同步
     *
     * @param blockHeader
     * @return
     */
    int insert(SimpleBlockHeader blockHeader);

    /**
     * 根据高度查询
     *
     * @param height
     * @return
     */
    SimpleBlockHeader selectByPrimaryKey(Integer height);

    /**
     * 查询最新区块
     *
     * @return
     */
    public SimpleBlockHeader findByLatest();

}