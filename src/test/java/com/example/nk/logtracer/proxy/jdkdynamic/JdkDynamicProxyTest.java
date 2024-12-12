package com.example.nk.logtracer.proxy.jdkdynamic;

import com.example.nk.logtracer.proxy.jdkdynamic.code.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;

@Slf4j
public class JdkDynamicProxyTest {

    @Test
    void callATest() {
        AInterface target = new AImpl();
        TimeInvocationHandler timeInvocationHandler = new TimeInvocationHandler(target);

        // 위의 interface와 invocationhandler정보를 통해서 프록시가 동적으로 jdk에서 생성되어서 처리가 되어진다.
        // 클래스 로더 정보, 인터페이스, 핸들러 로직을 넣어주면 됨.
        AInterface dynamicProxy = (AInterface) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), timeInvocationHandler);
        String result = dynamicProxy.call();
        log.info("call A 종료 result={}", result);
    }

    @Test
    void callBTest() {
        BInterface target = new BImpl();
        TimeInvocationHandler timeInvocationHandler = new TimeInvocationHandler(target);

        BInterface dynamicProxy = (BInterface) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), timeInvocationHandler);
        String result = dynamicProxy.call();
        log.info("callBTest 종료 result={}", result);

    }

}
