package io.nuls.dapp.communitygovernance.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 添加全局配置
 * Created by wangkun23 on 2018/9/10.
 */
@Configuration
public class AppBean {

    @Value("${app.devMode}")
    private Boolean devMode;

    /**
     * 是否是开发者模式
     *
     * @return
     */
    public Boolean isDevMode() {
        return devMode;
    }
}
