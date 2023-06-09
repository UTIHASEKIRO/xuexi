package com.zkzl.framework.sms.config;

import com.zkzl.framework.sms.core.client.SmsClientFactory;
import com.zkzl.framework.sms.core.client.impl.SmsClientFactoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 短信配置类
 *
 * @author 芋道源码
 */
@Configuration
public class ZkzlSmsAutoConfiguration {

    @Bean
    public SmsClientFactory smsClientFactory() {
        return new SmsClientFactoryImpl();
    }

}
