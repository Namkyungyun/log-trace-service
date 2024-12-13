package com.example.nk.logtracer.proxy.proxyfactory;

import com.example.nk.logtracer.proxy.common.advice.TimeAdvice;
import com.example.nk.logtracer.proxy.common.service.ConcreteService;
import com.example.nk.logtracer.proxy.common.service.ServiceImpl;
import com.example.nk.logtracer.proxy.common.service.ServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.AopUtils;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class ProxyFactoryTest {

    @Test
    @DisplayName("인터페이스가 있으면 JDK 동적 프록시 사용")
    void interfaceProxy() {
        ServiceInterface target = new ServiceImpl();

        // proxy factory를 이용해 프록시 생성 (타켓에 대한 동적 프록시)
        ProxyFactory proxyFactory = new ProxyFactory(target);
        // advice 주입
        proxyFactory.addAdvice(new TimeAdvice());
        ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();
        log.info("targetClass={}", target.getClass().getSimpleName());
        log.info("proxyClass={}", proxy.getClass());

        proxy.save();

        assertThat((AopUtils.isAopProxy(proxy))).isTrue();
        assertThat(AopUtils.isJdkDynamicProxy(proxy)).isTrue();
        assertThat(AopUtils.isCglibProxy(proxy)).isFalse();
    }

    @Test
    @DisplayName("구체클래스만 있으면 CGLIB 사용")
    void cglibProxy() {
        ConcreteService target = new ConcreteService();

        // proxy factory를 이용해 프록시 생성 (타켓에 대한 동적 프록시)
        ProxyFactory proxyFactory = new ProxyFactory(target);
        // advice 주입
        proxyFactory.addAdvice(new TimeAdvice());
        ConcreteService proxy = (ConcreteService) proxyFactory.getProxy();
        log.info("targetClass={}", target.getClass().getSimpleName());
        log.info("proxyClass={}", proxy.getClass().getSimpleName());

        proxy.call();

        assertThat((AopUtils.isAopProxy(proxy))).isTrue();
        assertThat(AopUtils.isJdkDynamicProxy(proxy)).isFalse();
        assertThat(AopUtils.isCglibProxy(proxy)).isTrue();
    }

    @Test
    @DisplayName("ProxyTargetClass 옵션을 사용하면 인터페이스가 있어도 CGLIB를 사용하고, 클래스 기반 프록시 사용")
    void proxyTargetClass() {
        ServiceInterface target = new ServiceImpl();

        // proxy factory를 이용해 프록시 생성 (타켓에 대한 동적 프록시)
        ProxyFactory proxyFactory = new ProxyFactory(target);
        // cglib를 사용하도록 설정 (serviceImpl 구체클래스를 상속받아서 cglib 프록시를 만듦.)
        proxyFactory.setProxyTargetClass(true);

        // advice 주입
        proxyFactory.addAdvice(new TimeAdvice());
        ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();
        log.info("targetClass={}", target.getClass().getSimpleName());
        log.info("proxyClass={}", proxy.getClass());

        proxy.save();

        assertThat((AopUtils.isAopProxy(proxy))).isTrue();
        assertThat(AopUtils.isJdkDynamicProxy(proxy)).isFalse();
        assertThat(AopUtils.isCglibProxy(proxy)).isTrue();
    }
}
