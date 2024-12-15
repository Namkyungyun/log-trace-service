package com.example.nk.logtracer.config.v6_aspect;

import com.example.nk.logtracer.config.ProxyAppV1Config;
import com.example.nk.logtracer.config.ProxyAppV2Config;
import com.example.nk.logtracer.config.v6_aspect.aspect.LogTraceAspect;
import com.example.nk.logtracer.threadlocal.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ProxyAppV1Config.class, ProxyAppV2Config.class})
public class AppConfig {

    @Bean
    LogTraceAspect logTraceAspect(LogTrace logTrace) {
        return new LogTraceAspect(logTrace);
    }
}
