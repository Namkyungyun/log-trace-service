package com.example.nk.logtracer.trace;

import com.example.nk.logtracer.trace.logtrace.ThreadLocalLogTrace;
import com.example.nk.logtracer.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogTraceConfig {

    @Bean(name="traceConfig")
    public LogTrace logTraceConfig() {
        return new ThreadLocalLogTrace();
    }
}
