package com.example.nk.logtracer.proxy.common.advice;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

@Slf4j
public class Advice2 implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        log.info("Advice2 실행");
        Object proceed = invocation.proceed();
        log.info("Advice2 종료");
        return proceed;
    }
}
