package io.nuls.dapp.communitygovernance.service;

import io.nuls.dapp.communitygovernance.model.SimpleBlockHeader;

/**
 * 区块高度同步操作
 */
public interface BlockSimpleService {
    /**
     * 保存本地区块
     *
     * @return
     */
    void saveLocalBlockHeader(SimpleBlockHeader blockHeader);

    /**
     * 查询最新区块
     *
     * @return
     */
    SimpleBlockHeader findByLatest();

    /**
     * 获取本地的缓存的区块高度
     *
     * @return
     */
    SimpleBlockHeader getLocalBlockHeader();
}
