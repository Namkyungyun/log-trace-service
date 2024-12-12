package com.example.nk.logtracer.config.v2_dynamicproxy.handler;

import com.example.nk.logtracer.threadlocal.TraceStatus;
import com.example.nk.logtracer.threadlocal.logtrace.LogTrace;
import org.springframework.util.PatternMatchUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class LogTraceFilterHandler implements InvocationHandler {

    private final LogTrace logTrace;
    private final Object target;
    private final String[] patterns;

    public LogTraceFilterHandler(LogTrace logTrace, Object target, String[] patterns) {
        this.logTrace = logTrace;
        this.target = target;
        this.patterns = patterns;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 로그가 남지않아야하는 메서드에 대해서는 메서드만 실행시키도록 필터처리를 넣어야 함.
        String methodName = method.getName();
        // save, requrest, reque*, *est 등의 패턴을 사용
        if(!PatternMatchUtils.simpleMatch(patterns, methodName)) {
            return method.invoke(target, args);
        }


        TraceStatus status = null;
        try {
            // 호출된 class명 & method명 추출하기
            String methodInfo = method.getDeclaringClass().getSimpleName() + "." + methodName;
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
