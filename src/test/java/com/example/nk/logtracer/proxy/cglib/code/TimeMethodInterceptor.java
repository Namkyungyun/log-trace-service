package com.example.nk.logtracer.proxy.cglib.code;


import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

@Slf4j
public class TimeMethodInterceptor implements MethodInterceptor {

    private final Object target;

    public TimeMethodInterceptor(Object target) {
        this.target = target;
    }


    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        log.info("TimeMethodInterceptor 실행");
        long startTime = System.currentTimeMillis();
        // cglib 메뉴얼에서 methodProxy를 사용해 메서드를 호출하는 것이 더 빠르다고 권장.
        Object result = proxy.invoke(target, args);

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;

        log.info("TimeMethodInterceptor 종료 resultTime={}", resultTime);
        return result;
    }
}
