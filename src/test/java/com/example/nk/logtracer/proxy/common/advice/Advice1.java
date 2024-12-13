package com.example.nk.logtracer.proxy.common.advice;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

@Slf4j
public class Advice1 implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        log.info("Advice1 실행");
        Object proceed = invocation.proceed();
        log.info("Advice1 종료");
        return proceed;
    }
}
