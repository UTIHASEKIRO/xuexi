/*
 *
 *      Copyright (c) 2018-2025, eco All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 *  this list of conditions and the following disclaimer.
 *  Redistributions in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in the
 *  documentation and/or other materials provided with the distribution.
 *  Neither the name of the pig4cloud.com developer nor the names of its
 *  contributors may be used to endorse or promote products derived from
 *  this software without specific prior written permission.
 *  Author: eco
 *
 */

package com.zkzl.framework.log.aspect;

import cn.hutool.json.JSONUtil;
import com.zkzl.framework.log.annotation.SysLog;
import com.zkzl.framework.log.util.WebUtils;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 操作日志使用spring event异步入库
 *
 * @author L.cm
 */
@Slf4j
@Aspect
@AllArgsConstructor
public class SysLogAspect {

    @SneakyThrows
    @Around("@annotation(sysLog)")
    public Object around(ProceedingJoinPoint point, SysLog sysLog) {
        return this.commonAround(point);
    }

    /**
     * 拦截 没有加 SysLog 注解的请求
	 * @author zdm
     * @return java.lang.Object
     */
    @SneakyThrows
    @Around("!@annotation(com.zkzl.framework.log.annotation.SysLog) && !@within(com.zkzl.framework.log.annotation.DisabledSysLog) && @within(org.springframework.web.bind.annotation.RestController)")
    public Object around(ProceedingJoinPoint point) {
        return this.commonAround(point);
    }

    private Object commonAround(ProceedingJoinPoint point) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        String ipAddr = WebUtils.getIpAddr(request);
        String url = request.getRequestURL().toString();
        String requestMethod = request.getMethod();
        log.info("======= vv请求源IP:[{}],请求URL:[{}],请求方式:[{}] ======= start", ipAddr, url, requestMethod);
        String strClassName = point.getTarget().getClass().getName();
        String strMethodName = point.getSignature().getName();
        log.info("当前执行类名 >>> [{}]", strClassName);
        log.info("当前执行方法名 >>> [{}]", strMethodName);
        Object[] args = point.getArgs();
        List<Object> argList = new ArrayList<>();
        for (Object arg : args) {
            if (arg instanceof HttpServletResponse || arg instanceof HttpServletRequest || arg instanceof MultipartFile) {
                continue;
            }
            argList.add(arg);
        }
        log.info("当前执行方法参数 >>> [{}]", JSONUtil.toJsonStr(argList));
        // 发送异步日志事件
        Long startTime = System.currentTimeMillis();

        long time;
        Object obj;
        try {
            obj = point.proceed();
        } finally {
            Long endTime = System.currentTimeMillis();
            time = endTime-startTime;
        }
        log.info("当前执行方法返回值 >>> [{}]", JSONUtil.toJsonStr(obj));
        log.info("======= 请求源IP:[{}],请求URL:[{}],请求方式:[{}],耗时:[{}] ms ======= end", ipAddr, url, requestMethod, time);
        return obj;
    }
}
