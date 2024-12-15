package com.example.nk.logtracer.config.v5_autoproxy;

import com.example.nk.logtracer.config.ProxyAppV1Config;
import com.example.nk.logtracer.config.ProxyAppV2Config;
import com.example.nk.logtracer.config.v3_proxyfactory.advice.LogTraceAdvice;
import com.example.nk.logtracer.threadlocal.logtrace.LogTrace;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultBeanFactoryPointcutAdvisor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ProxyAppV1Config.class, ProxyAppV2Config.class})
public class AutoProxyConfig {

    /* 아래의 메서드 명이 들어가는 lib에 속하는 구현파트에서도 프록시가 생성될 수 있음. */
//    @Bean
    public Advisor advisor1(LogTrace logTrace) {
        // advice
        LogTraceAdvice advice = new LogTraceAdvice(logTrace);
        NameMatchMethodPointcutAdvisor advisor = new NameMatchMethodPointcutAdvisor(advice);
        advisor.setMappedNames("request*", "save*", "order*");

        return advisor;
    }

    /* 위의 단점을 보안해 실무에서는 aspectJ만 거의 사용된다.
    *  aspectJ 표현식을 사용하며 아래와 같이 규정한 패키지에 속하는 곳의 프록시만 생성되게 지정한다. */
//    @Bean
    public Advisor advisor2(LogTrace logTrace) {
        // aspectJ pointcut
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        // 단순 'package'기준으로 포인트컷 매칭
        pointcut.setExpression("execution(* com.example.nk.logtracer.app.proxy..*(..))");

        // advice
        LogTraceAdvice advice = new LogTraceAdvice(logTrace);
        return new DefaultPointcutAdvisor(pointcut, advice);
    }

    /* 위의 package기준의 포인트컷 매칭에서 예외를 추가한 파트 */
    @Bean
    public Advisor advisor3(LogTrace logTrace) {
        // aspectJ pointcut
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* com.example.nk.logtracer.app.proxy..*(..)) && !execution(* com.example.nk.logtracer.app.proxy..noLog(..))");

        // advice
        LogTraceAdvice advice = new LogTraceAdvice(logTrace);
        return new DefaultPointcutAdvisor(pointcut, advice);
    }


}
