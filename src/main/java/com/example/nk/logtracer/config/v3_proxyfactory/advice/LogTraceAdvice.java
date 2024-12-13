package com.example.nk.logtracer.config.v3_proxyfactory.advice;

import com.example.nk.logtracer.threadlocal.TraceStatus;
import com.example.nk.logtracer.threadlocal.logtrace.LogTrace;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

@Slf4j
public class LogTraceAdvice implements MethodInterceptor {
    private final LogTrace logTrace;

    public LogTraceAdvice(LogTrace logTrace) {
        this.logTrace = logTrace;
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        TraceStatus status = null;
        try {
            // 클래스 및 메서드명 가져오기
            String methodName = invocation.getClass().getSimpleName()+ "." + invocation.getMethod().getName();

            status = logTrace.begin(methodName);
            Object result = invocation.proceed();
            logTrace.end(status);

            return result;

        } catch(Exception e) {
            logTrace.exception(status,e);
            throw e;
        }
    }
}
