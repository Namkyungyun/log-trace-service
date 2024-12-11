package com.example.nk.logtracer.proxy.pureproxy.proxy;

import com.example.nk.logtracer.proxy.pureproxy.proxy.code.CacheProxy;
import com.example.nk.logtracer.proxy.pureproxy.proxy.code.ProxyPatternClient;
import com.example.nk.logtracer.proxy.pureproxy.proxy.code.RealSubject;
import org.junit.jupiter.api.Test;

public class ProxyPatternTest {

    @Test
    void noProxyTest() {
        /* 각 execute  1초, 총 3초가 걸림*/
        RealSubject realSubject = new RealSubject();
        ProxyPatternClient client
                = new ProxyPatternClient(realSubject);

        client.execute();
        client.execute();
        client.execute();
        /*
        * 캐시란 ? :
        * 위의 데이터가 한번 조회하면
        * 변하지 않는 데이터라면
        * 어딘가에 보관해두고 이미 조회한
        * 데이터를 사용하는 것이 성능상 좋음
        * -> 캐시 또한 접근 자체를 제어하는 기능 중 하나.
        * */
    }


    @Test
    void cacheProxyTest() {
        RealSubject realSubject = new RealSubject();
        CacheProxy cacheProxy = new CacheProxy(realSubject);
        ProxyPatternClient client = new ProxyPatternClient(cacheProxy);

        client.execute(); // target까지 호출
        client.execute(); // 프록시의 operator만 동작
        client.execute(); // 프록시의 operator만 동작
    }

}
