package com.example.nk.logtracer;

import com.example.nk.logtracer.config.v1_proxy.ConcreteProxyConfig;
import com.example.nk.logtracer.config.v2_dynamicproxy.DynamicProxyBasicConfig;
import com.example.nk.logtracer.config.v2_dynamicproxy.DynamicProxyFilterConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;


@Import(DynamicProxyFilterConfig.class)
@SpringBootApplication(scanBasePackages = {"com.example.nk.logtracer.app.proxy.v1"})
public class LogTracerApplication {

    public static void main(String[] args) {

//        ConfigurableApplicationContext context =
                SpringApplication.run(LogTracerApplication.class, args);
//        System.out.println(Arrays.toString(context.getBeanDefinitionNames()));
    }

}
