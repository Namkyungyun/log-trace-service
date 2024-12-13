package com.example.nk.logtracer.proxy.postprocessor;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.junit.jupiter.api.Assertions.assertThrows;

@Slf4j
public class BeanProcessorTest {

    @Test
    void basicConfig() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanProcessorConfig.class);
        B b = applicationContext.getBean("beanA", B.class);
        b.callB();

        assertThrows(NoSuchBeanDefinitionException.class, () -> applicationContext.getBean(A.class));
    }


    @Configuration
    static class BeanProcessorConfig {
        @Bean(name="beanA")
        public A a() {
            return new A();
        }

        @Bean
        public AToBPostProcessor aToBPostProcessor() {
            return new AToBPostProcessor();
        }
    }

    static class A {
        public void callA() {
            log.info("callA");
        }
    }

    static class B {
        public void callB() {
            log.info("callB");
        }
    }

    @Slf4j
    static class AToBPostProcessor implements BeanPostProcessor {

        @Override
        public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
            log.info("beanName={}, bean={}", beanName, bean);

            if(bean instanceof A) {
                return new B();
            }

            return bean;
        }
    }

}
