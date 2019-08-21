package io.nuls.dapp.communitygovernance.config;

import io.nuls.dapp.communitygovernance.intercepter.HandlerLoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

/**
 * Description:
 * Author: zsj
 * Date:  2017/12/18 0018
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
    /**
     * 拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HandlerLoginInterceptor());
        super.addInterceptors(registry);
    }

    /**
     * 允许跨域访问
     *
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/cg/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowedHeaders("*");
    }
}
