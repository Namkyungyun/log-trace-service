package com.example.nk.logtracer.config;

import com.example.nk.logtracer.app.proxy.v2.ProxyOrderControllerV2;
import com.example.nk.logtracer.app.proxy.v2.ProxyOrderRepositoryV2;
import com.example.nk.logtracer.app.proxy.v2.ProxyOrderServiceV2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProxyAppV2Config {
    @Bean
    public ProxyOrderRepositoryV2 proxyOrderRepositoryV2() {
        return new ProxyOrderRepositoryV2();
    }

    @Bean
    public ProxyOrderServiceV2 proxyOrderServiceV2() {
        return new ProxyOrderServiceV2(proxyOrderRepositoryV2());
    }

    @Bean
    public ProxyOrderControllerV2 proxyOrderControllerV2() {
        return new ProxyOrderControllerV2(proxyOrderServiceV2());
    }


}
