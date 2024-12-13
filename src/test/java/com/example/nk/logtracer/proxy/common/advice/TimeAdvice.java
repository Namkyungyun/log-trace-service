package com.example.nk.logtracer.proxy.common.advice;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

@Slf4j
public class TimeAdvice implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        // proxy factory를 만들 때 이미 해당 instanced에서
        // Target을 넣어주기 때문에 따로 target인자를 생성자에 안넣어도 됨.
        log.info("TimeAdvice 실행");
        long startTime = System.currentTimeMillis();
        Object result = invocation.proceed();

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("TimeAdvice 종료 resultTime={}", resultTime);

        return result;
    }
}
