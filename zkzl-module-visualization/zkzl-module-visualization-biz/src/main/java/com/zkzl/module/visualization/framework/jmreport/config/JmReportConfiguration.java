package com.zkzl.module.visualization.framework.jmreport.config;

import com.zkzl.module.system.api.oauth2.OAuth2TokenApi;
import com.zkzl.module.visualization.framework.jmreport.core.service.JmReportTokenServiceImpl;
import org.jeecg.modules.jmreport.api.JmReportTokenServiceI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 积木报表的配置类
 *
 * @author 芋道源码
 */
@Configuration
@ComponentScan(basePackages = "org.jeecg.modules.jmreport") // 扫描积木报表的包
public class JmReportConfiguration {

    @Bean
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public JmReportTokenServiceI jmReportTokenService(OAuth2TokenApi oAuth2TokenApi) {
        return new JmReportTokenServiceImpl(oAuth2TokenApi);
    }

}
