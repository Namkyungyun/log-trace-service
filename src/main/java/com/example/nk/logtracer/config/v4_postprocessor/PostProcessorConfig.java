package com.example.nk.logtracer.config.v4_postprocessor;

import com.example.nk.logtracer.config.ProxyAppV1Config;
import com.example.nk.logtracer.config.ProxyAppV2Config;
import com.example.nk.logtracer.config.v3_proxyfactory.advice.LogTraceAdvice;
import com.example.nk.logtracer.threadlocal.logtrace.LogTrace;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.Advisor;
import org.springframework.aop.support.NameMatchMethodPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Slf4j
@Configuration
@Import({ProxyAppV1Config.class,  ProxyAppV2Config.class})
public class PostProcessorConfig {

    @Bean
    public PackageLogTracePostProcessor packageLogTracePostProcessor(LogTrace logTrace) {
        return new PackageLogTracePostProcessor(getAdvisor(logTrace), "com.example.nk.logtracer.app");
    }


    private Advisor getAdvisor(LogTrace logTrace) {
        final LogTraceAdvice advice = new LogTraceAdvice(logTrace);

        NameMatchMethodPointcutAdvisor advisor = new NameMatchMethodPointcutAdvisor(advice);
        advisor.setMappedNames("request*", "save*", "order*");

        return advisor;
    }


}
