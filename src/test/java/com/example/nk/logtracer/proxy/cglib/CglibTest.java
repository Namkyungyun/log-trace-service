package com.example.nk.logtracer.proxy.cglib;

import com.example.nk.logtracer.proxy.cglib.code.TimeMethodInterceptor;
import com.example.nk.logtracer.proxy.common.service.ConcreteService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.cglib.proxy.Enhancer;

@Slf4j
public class CglibTest {

    @Test
    void cglib() {
        ConcreteService target = new ConcreteService();

        Enhancer enhancer = new Enhancer();
        // 동적 프록시로 만들 클래스 등록
        enhancer.setSuperclass(ConcreteService.class);
        // cglib 핸들러 주입
        enhancer.setCallback(new TimeMethodInterceptor(target));
        // 동적 프록시 생성
        ConcreteService proxy = (ConcreteService) enhancer.create();
        // 생성된 프록시 확인 로그
        log.info("targetClass={}", target.getClass());
        log.info("proxyClass={}", proxy.getClass());

        // 프록시로 메서드 호출
        proxy.call();

    }
}
