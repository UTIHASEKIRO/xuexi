package com.zkzl.framework.banner.config;

import com.zkzl.framework.banner.core.BannerApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Banner 的自动配置类
 *
 * @author 芋道源码
 */
@Configuration
public class ZkzlBannerAutoConfiguration {

    @Bean
    public BannerApplicationRunner bannerApplicationRunner() {
        return new BannerApplicationRunner();
    }

}
