package com.example.nk.logtracer;

import com.example.nk.logtracer.config.ProxyAppV1Config;
import com.example.nk.logtracer.config.ProxyAppV2Config;
import com.example.nk.logtracer.config.v1_proxy.InterfaceProxyConfig;
import com.example.nk.logtracer.config.v2_proxy.ConcreteProxyConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;

import java.util.Arrays;


@Import(ConcreteProxyConfig.class)
@SpringBootApplication(scanBasePackages = {"com.example.nk.logtracer.app.proxy.v2"})
public class LogTracerApplication {

    public static void main(String[] args) {

//        ConfigurableApplicationContext context =
                SpringApplication.run(LogTracerApplication.class, args);
//        System.out.println(Arrays.toString(context.getBeanDefinitionNames()));
    }

}
