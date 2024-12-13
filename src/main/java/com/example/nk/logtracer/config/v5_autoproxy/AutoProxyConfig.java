package com.example.nk.logtracer.config.v5_autoproxy;

import com.example.nk.logtracer.config.ProxyAppV1Config;
import com.example.nk.logtracer.config.ProxyAppV2Config;
import com.example.nk.logtracer.config.v3_proxyfactory.advice.LogTraceAdvice;
import com.example.nk.logtracer.threadlocal.logtrace.LogTrace;
import org.springframework.aop.Advisor;
import org.springframework.aop.support.NameMatchMethodPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ProxyAppV1Config.class, ProxyAppV2Config.class})
public class AutoProxyConfig {

    @Bean
    public Advisor advisor1(LogTrace logTrace) {
        // advice
        LogTraceAdvice advice = new LogTraceAdvice(logTrace);
        NameMatchMethodPointcutAdvisor advisor = new NameMatchMethodPointcutAdvisor(advice);
        advisor.setMappedNames("request*", "save*", "order*");

        return advisor;
    }


}
