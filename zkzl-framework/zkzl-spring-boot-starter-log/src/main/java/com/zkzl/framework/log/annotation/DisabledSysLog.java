package com.zkzl.framework.log.annotation;

import java.lang.annotation.*;

/**
 * 禁用 操作日志注解
 *
 * @author zdm
 * @date 2022/3/8 10:56
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DisabledSysLog { }
