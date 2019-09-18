package io.nuls.dapp.communitygovernance.scheduled;

import io.nuls.dapp.communitygovernance.model.SimpleBlockHeader;
import io.nuls.dapp.communitygovernance.service.BlockSimpleService;
import io.nuls.dapp.communitygovernance.service.BlockSyncService;
import io.nuls.dapp.communitygovernance.service.api.BlockServiceApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 解析区块的定时器
 *
 * @author: PierreLuo
 * @date: 2019-08-09
 */
@Component
public class BlockScheduled {

    final Logger logger = LoggerFactory.getLogger(getClass());
    private boolean initialLoaded = false;
    private SyncError syncError = new SyncError();
    @Resource
    private BlockServiceApi blockServiceApi;
    @Resource
    private BlockSimpleService blockSimpleService;
    @Resource
    private BlockSyncService blockSyncService;

    /**
     * 检测区块的高度变化 查询交易数据
     */
    @Scheduled(cron = "0/10 * * * * *")
    public void syncHeight() {
        if(syncError.isError()) {
            logger.error("同步区块错误 height: {}", syncError.getErrorHeight());
            return;
        }
        initialLoad();

        Long localBlockHeight = blockSimpleService.getLocalBlockHeader().getHeight();
        try {
            logger.info("每10秒检测区块高度,检测区块的高度变化 查询交易数据 height: {}", localBlockHeight);
            Long height = blockServiceApi.getNewestBlockHeader().getHeight();
            Long between = height - localBlockHeight;
            logger.info("between {}", between);
            SimpleBlockHeader simpleBlockHeader;
            for (int i = 1; i <= between; i++) {
                localBlockHeight = localBlockHeight + 1;
                /**
                 * 同步并解析数据
                 *
                 */
                try {
                    simpleBlockHeader = blockSyncService.syncBlock(localBlockHeight);
                    blockSimpleService.saveLocalBlockHeader(simpleBlockHeader);
                } catch (Exception e) {
                    logger.error("syncHeight error ", e);
                    syncError.setError();
                    syncError.setErrorHeight(localBlockHeight);
                    break;
                }
                if(logger.isDebugEnabled()) {
                    logger.debug("localBlockHeight {}", localBlockHeight);
                }
            }
        } catch (Exception e) {
            logger.error("syncHeight error ", e);
            syncError.setError();
            syncError.setErrorHeight(localBlockHeight);
        }
    }

    private void initialLoad() {
        if(initialLoaded) {
            if(logger.isDebugEnabled()) {
                logger.debug("something - {}");
            }
            return;
        }
        initialLoaded = true;
    }
}
