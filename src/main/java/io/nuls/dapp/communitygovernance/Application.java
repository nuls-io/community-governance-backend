package io.nuls.dapp.communitygovernance;

import io.nuls.dapp.communitygovernance.service.BlockSyncService;
import io.nuls.dapp.communitygovernance.service.IAccountBalanceProcessor;
import io.nuls.dapp.communitygovernance.service.IEventProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * POCM后台
 * @author: PierreLuo
 * @date: 2019-08-12
 */
@EnableScheduling
@SpringBootApplication
public class Application implements CommandLineRunner {

    final Logger logger = LoggerFactory.getLogger(getClass());

    public static void main(String[] args) {
        //started
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    private ApplicationContext appContext;

    @Override
    public void run(String... strings) throws Exception {
        BlockSyncService blockSyncService = appContext.getBean(BlockSyncService.class);
        String[] eventProcessors = appContext.getBeanNamesForType(IEventProcessor.class);
        for(String eventProcessor : eventProcessors) {
            blockSyncService.addEventProcessor((IEventProcessor) appContext.getBean(eventProcessor));
        }
        String[] accountBalanceProcessors = appContext.getBeanNamesForType(IAccountBalanceProcessor.class);
        for(String accountBalanceProcessor : accountBalanceProcessors) {
            blockSyncService.addAccountBalanceProcessor((IAccountBalanceProcessor) appContext.getBean(accountBalanceProcessor));
        }
        logger.info("load processor completed.");
    }
}
