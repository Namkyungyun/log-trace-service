package com.example.nk.logtracer.config.v2_dynamicproxy.handler;

import com.example.nk.logtracer.threadlocal.TraceStatus;
import com.example.nk.logtracer.threadlocal.logtrace.LogTrace;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class LogTraceBasicHandler implements InvocationHandler {

    private final LogTrace logTrace;
    private final Object target;

    public LogTraceBasicHandler(LogTrace logTrace, Object target) {
        this.logTrace = logTrace;
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        TraceStatus status = null;
        try {
            // 호출된 class명 & method명 추출하기
            String methodInfo = method.getDeclaringClass().getSimpleName() + "." + method.getName();
            status = logTrace.begin(methodInfo);

            //호출 메서드 실행
            Object result = method.invoke(target, args);
            logTrace.end(status);

            return result;

        } catch(Exception e) {
            logTrace.exception(status, e);
            throw e;
        }
    }
}
