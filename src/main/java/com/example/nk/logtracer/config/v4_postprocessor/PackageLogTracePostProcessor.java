package com.example.nk.logtracer.config.v4_postprocessor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

@Slf4j
public class PackageLogTracePostProcessor implements BeanPostProcessor {

    private final Advisor advisor;
    private final String basePackage;

    public PackageLogTracePostProcessor(Advisor advisor, String basePackage) {
        this.advisor = advisor;
        this.basePackage = basePackage;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        log.info("bean={}, beanName={}", bean.getClass().getSimpleName(), beanName);
        // 1. bean의 packa 읽기
        String packageName = bean.getClass().getPackageName();

        // 2. package에 포함된 bean 필터
        if (!packageName.startsWith(basePackage)) {
            return bean;
        }
//        log.info(" =====================> created proxy={}", bean.getClass().getPackageName());

        // 3. 프록시 생성
        ProxyFactory proxyFactory = new ProxyFactory(bean);
        proxyFactory.addAdvisor(advisor);
        Object proxy = proxyFactory.getProxy();
        log.info("target={} created proxy={}", bean.getClass().getSimpleName(), proxy.getClass().getSimpleName());

        return proxy;
    }
}
