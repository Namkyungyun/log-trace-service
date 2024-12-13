package com.example.nk.logtracer.config.v1_proxy;

import com.example.nk.logtracer.app.proxy.v1.*;
import com.example.nk.logtracer.config.v1_proxy.interface_proxy.OrderControllerInterfaceProxy;
import com.example.nk.logtracer.config.v1_proxy.interface_proxy.OrderRepositoryInterfaceProxy;
import com.example.nk.logtracer.config.v1_proxy.interface_proxy.OrderServiceInterfaceProxy;
import com.example.nk.logtracer.threadlocal.logtrace.LogTrace;
import com.example.nk.logtracer.threadlocal.logtrace.LogTraceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InterfaceProxyConfig {

    @Bean
    ProxyOrderControllerV1 proxyOrderControllerV1(LogTrace logTrace) {
        ProxyOrderControllerV1 impl = new ProxyOrderControllerV1Impl(proxyOrderServiceV1(logTrace));
        return new OrderControllerInterfaceProxy(logTrace, impl);
    }


    @Bean
    ProxyOrderServiceV1 proxyOrderServiceV1(LogTrace logTrace) {
        ProxyOrderServiceV1Impl impl = new ProxyOrderServiceV1Impl(proxyOrderRepositoryV1(logTrace));
        return new OrderServiceInterfaceProxy(logTrace, impl);
    }

    @Bean
    ProxyOrderRepositoryV1 proxyOrderRepositoryV1(LogTrace logTrace) {
        ProxyOrderRepositoryV1Impl impl = new ProxyOrderRepositoryV1Impl();
        return new OrderRepositoryInterfaceProxy(logTrace, impl);
    }
}
