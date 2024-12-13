package com.example.nk.logtracer.config.v3_proxyfactory;

import com.example.nk.logtracer.app.proxy.v1.*;
import com.example.nk.logtracer.config.v3_proxyfactory.advice.LogTraceAdvice;
import com.example.nk.logtracer.threadlocal.logtrace.LogTrace;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.NameMatchMethodPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProxyFactoryConfig {

    @Bean
    ProxyOrderControllerV1 proxyOrderControllerV1(LogTrace logTrace) {
        // 1. target
        ProxyOrderControllerV1 target = new ProxyOrderControllerV1Impl(proxyOrderServiceV1(logTrace));

        // 2. proxy factory
        ProxyFactory proxyFactory = new ProxyFactory(target);

        // 3. advisor 등록
        Advisor advisor = getAdvisor(logTrace);
        proxyFactory.addAdvisor(advisor);

        // 4. 형변환
        return (ProxyOrderControllerV1) proxyFactory.getProxy();
    }

    @Bean
    ProxyOrderServiceV1 proxyOrderServiceV1(LogTrace logTrace) {
        // 1. target
        ProxyOrderServiceV1 target = new ProxyOrderServiceV1Impl(proxyOrderRepositoryV1(logTrace));

        // 2. proxy factory
        ProxyFactory proxyFactory = new ProxyFactory(target);

        // 3. advisor 등록
        proxyFactory.addAdvisor(getAdvisor(logTrace));

        // 4. 형변환
        return (ProxyOrderServiceV1) proxyFactory.getProxy();
    }


    @Bean
    ProxyOrderRepositoryV1 proxyOrderRepositoryV1(LogTrace logTrace) {
        // 1. target
        ProxyOrderRepositoryV1 target = new ProxyOrderRepositoryV1Impl();

        // 2. proxy factory
        ProxyFactory proxyFactory = new ProxyFactory(target);

        // 3. advisor 등록
        Advisor advisor = getAdvisor(logTrace);
        proxyFactory.addAdvisor(advisor);

        // 4. 형변환
        return (ProxyOrderRepositoryV1) proxyFactory.getProxy();
    }


    private Advisor getAdvisor(LogTrace logTrace) {
        LogTraceAdvice advice = new LogTraceAdvice(logTrace);

        NameMatchMethodPointcutAdvisor advisor = new NameMatchMethodPointcutAdvisor(advice);
        advisor.setMappedNames("save*", "request*", "order*");

        return advisor;
    }

}
