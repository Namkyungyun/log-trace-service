package com.example.nk.logtracer;

import com.example.nk.logtracer.config.v1_proxy.ConcreteProxyConfig;
import com.example.nk.logtracer.config.v2_dynamicproxy.DynamicProxyBasicConfig;
import com.example.nk.logtracer.config.v2_dynamicproxy.DynamicProxyFilterConfig;
import com.example.nk.logtracer.config.v3_proxyfactory.ProxyFactoryConfig;
import com.example.nk.logtracer.config.v4_postprocessor.PostProcessorConfig;
import com.example.nk.logtracer.config.v5_autoproxy.AutoProxyConfig;
import com.example.nk.logtracer.threadlocal.logtrace.LogTrace;
import com.example.nk.logtracer.threadlocal.logtrace.LogTraceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;


//@Import(DynamicProxyFilterConfig.class)
//@Import(ProxyFactoryConfig.class)
//@Import(PostProcessorConfig.class)
@Import(AutoProxyConfig.class)
@SpringBootApplication(scanBasePackages = {"com.example.nk.logtracer.app.proxy.v3"})
public class LogTracerApplication {

    public static void main(String[] args) {

//        ConfigurableApplicationContext context =
                SpringApplication.run(LogTracerApplication.class, args);
//        System.out.println(Arrays.toString(context.getBeanDefinitionNames()));
    }


    @Bean
    LogTrace logTrace() {
        return new LogTraceImpl();
    }

}
