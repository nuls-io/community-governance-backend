package io.nuls.dapp.communitygovernance.service.impl;

import io.nuls.dapp.communitygovernance.mapper.BlockSimpleMapper;
import io.nuls.dapp.communitygovernance.model.SimpleBlockHeader;
import io.nuls.dapp.communitygovernance.service.BlockSimpleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.ConcurrentHashMap;

import static io.nuls.dapp.communitygovernance.util.TimeUtil.now;

/**
 * 区块高度同步操作
 * Created by wangkun23 on 2018/9/14.
 */
@Service
public class BlockSimpleServiceImpl implements BlockSimpleService {

    private static final String BLOCK_HEADER_KEY = "LOCAL_BLOCK_HEADER";
    private static final ConcurrentHashMap<String, SimpleBlockHeader> localBlockHeaderMaps = new ConcurrentHashMap<>();

    @Resource
    BlockSimpleMapper blockSimpleMapper;

    /**
     * 保存本地区块头
     *
     * @return
     */
    @Override
    public void saveLocalBlockHeader(SimpleBlockHeader blockHeader) {
        localBlockHeaderMaps.put(BLOCK_HEADER_KEY, blockHeader);
        this.save(blockHeader);
    }

    /**
     * 查询最新区块
     *
     * @return
     */
    @Override
    public SimpleBlockHeader findByLatest() {
        SimpleBlockHeader blockHeight = blockSimpleMapper.findByLatest();
        if (blockHeight == null) {
            return null;
        } else {
            return blockHeight;
        }
    }

    /**
     * 获取本地的缓存的区块高度
     *
     * @return
     */
    @Override
    public SimpleBlockHeader getLocalBlockHeader() {
        //写到一个是否有，没有的话需要从0开始
        SimpleBlockHeader localBlockHeader = localBlockHeaderMaps.get(BLOCK_HEADER_KEY);
        if (localBlockHeader == null) {
            localBlockHeader = this.findByLatest();
            localBlockHeaderMaps.putIfAbsent(BLOCK_HEADER_KEY, localBlockHeader);
        }
        return localBlockHeader;
    }

    /**
     * 保存
     *
     * @param height
     */
    private void save(SimpleBlockHeader blockHeader) {
        blockHeader.setCreateTime(now());
        blockSimpleMapper.insert(blockHeader);
    }
}
