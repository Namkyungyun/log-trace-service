package com.example.nk.logtracer.threadlocal;

import com.example.nk.logtracer.threadlocal.logtrace.LogTraceImpl;
import com.example.nk.logtracer.threadlocal.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogTraceConfig {

    @Bean(name="traceConfig")
    public LogTrace logTraceConfig() {
        return new LogTraceImpl();
    }
}
