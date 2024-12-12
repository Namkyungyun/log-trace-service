package com.example.nk.logtracer.config.v2_proxy;

import com.example.nk.logtracer.app.proxy.v2.ProxyOrderControllerV2;
import com.example.nk.logtracer.app.proxy.v2.ProxyOrderRepositoryV2;
import com.example.nk.logtracer.app.proxy.v2.ProxyOrderServiceV2;
import com.example.nk.logtracer.config.v2_proxy.concrete_proxy.OrderControllerConcreteProxy;
import com.example.nk.logtracer.config.v2_proxy.concrete_proxy.OrderRepositoryConcreteProxy;
import com.example.nk.logtracer.config.v2_proxy.concrete_proxy.OrderServiceConcreteProxy;
import com.example.nk.logtracer.threadlocal.logtrace.LogTrace;
import com.example.nk.logtracer.threadlocal.logtrace.LogTraceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConcreteProxyConfig {

    @Bean
    ProxyOrderRepositoryV2 proxyOrderRepositoryV2(LogTrace logTrace) {
        final ProxyOrderRepositoryV2 proxyOrderRepositoryV2 = new ProxyOrderRepositoryV2();
        return new OrderRepositoryConcreteProxy(proxyOrderRepositoryV2, logTrace);
    }

    @Bean
    ProxyOrderServiceV2 proxyOrderServiceV2(LogTrace logTrace) {
        final ProxyOrderServiceV2 proxyOrderServiceV2 = new ProxyOrderServiceV2(proxyOrderRepositoryV2(logTrace));
        return new OrderServiceConcreteProxy(proxyOrderServiceV2, logTrace);
    }

    @Bean
    ProxyOrderControllerV2 proxyOrderControllerV2(LogTrace logTrace) {
        final ProxyOrderControllerV2 proxyOrderControllerV2 = new ProxyOrderControllerV2(proxyOrderServiceV2(logTrace));
        return new OrderControllerConcreteProxy(proxyOrderControllerV2, logTrace);
    }


    @Bean
    LogTrace logTrace() {
        return new LogTraceImpl();
    }

}
