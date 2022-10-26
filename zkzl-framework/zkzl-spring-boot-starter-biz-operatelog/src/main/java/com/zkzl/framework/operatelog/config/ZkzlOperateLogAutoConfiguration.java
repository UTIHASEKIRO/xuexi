package com.zkzl.framework.operatelog.config;

import com.zkzl.framework.operatelog.core.aop.OperateLogAspect;
import com.zkzl.framework.operatelog.core.service.OperateLogFrameworkService;
import com.zkzl.framework.operatelog.core.service.OperateLogFrameworkServiceImpl;
import com.zkzl.module.system.api.logger.OperateLogApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZkzlOperateLogAutoConfiguration {

    @Bean
    public OperateLogAspect operateLogAspect() {
        return new OperateLogAspect();
    }

    @Bean
    public OperateLogFrameworkService operateLogFrameworkService(OperateLogApi operateLogApi) {
        return new OperateLogFrameworkServiceImpl(operateLogApi);
    }

}
