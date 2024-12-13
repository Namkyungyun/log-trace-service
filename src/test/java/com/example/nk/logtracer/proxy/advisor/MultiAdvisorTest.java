package com.example.nk.logtracer.proxy.advisor;

import com.example.nk.logtracer.proxy.common.advice.Advice1;
import com.example.nk.logtracer.proxy.common.advice.Advice2;
import com.example.nk.logtracer.proxy.common.service.ServiceImpl;
import com.example.nk.logtracer.proxy.common.service.ServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

@Slf4j
public class MultiAdvisorTest {

    @Test
    @DisplayName("여러 프록시 ||  하나의 팩토리에 여러 개의 advisor를 등록하는 테스트")
    void multiAdvisorTest1() { // 프록시팩토리당 하나의 어드바이저만 등록할 수 있는 구조임을 확잉

        // client -> proxy2(advisor2) -> proxy1(advisor1) -> target
        // advisior
        DefaultPointcutAdvisor advisor1 = new DefaultPointcutAdvisor(Pointcut.TRUE, new Advice1());
        DefaultPointcutAdvisor advisor2 = new DefaultPointcutAdvisor(Pointcut.TRUE, new Advice2());

        ServiceInterface target = new ServiceImpl();
        final ProxyFactory proxyFactory = new ProxyFactory(target);

        proxyFactory.addAdvisor(advisor2);
        proxyFactory.addAdvisor(advisor1);

        ServiceInterface result = (ServiceInterface) proxyFactory.getProxy();
        result.save();
    }
}
