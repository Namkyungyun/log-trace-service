package com.example.nk.logtracer.config.v2_dynamicproxy;

import com.example.nk.logtracer.app.proxy.v1.*;
import com.example.nk.logtracer.config.v2_dynamicproxy.handler.LogTraceBasicHandler;
import com.example.nk.logtracer.threadlocal.logtrace.LogTrace;
import com.example.nk.logtracer.threadlocal.logtrace.LogTraceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Proxy;

@Configuration
public class DynamicProxyBasicConfig {


    @Bean
    ProxyOrderRepositoryV1 proxyOrderRepositoryV1(LogTrace logTrace) {
        final ProxyOrderRepositoryV1 target = new ProxyOrderRepositoryV1Impl();
        final LogTraceBasicHandler handler = new LogTraceBasicHandler(logTrace, target);
        Object proxy = Proxy.newProxyInstance(target.getClass().getClassLoader(),
                    target.getClass().getInterfaces(),
                    handler);

        return (ProxyOrderRepositoryV1) proxy;
    }

    @Bean
    ProxyOrderServiceV1 proxyOrderServiceV1(LogTrace logTrace) {
        final ProxyOrderServiceV1 target = new ProxyOrderServiceV1Impl(proxyOrderRepositoryV1(logTrace));
        final LogTraceBasicHandler handler = new LogTraceBasicHandler(logTrace, target);
        Object proxy = Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                handler);

        return (ProxyOrderServiceV1) proxy;
    }

    @Bean
    ProxyOrderControllerV1 proxyOrderControllerV1(LogTrace logTrace) {
        final ProxyOrderControllerV1 target = new ProxyOrderControllerV1Impl(proxyOrderServiceV1(logTrace));
        final LogTraceBasicHandler handler = new LogTraceBasicHandler(logTrace, target);
        Object proxy = Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                handler);

        return (ProxyOrderControllerV1) proxy;

    }


    @Bean
    LogTrace logTrace() {
        return new LogTraceImpl();
    }

}
