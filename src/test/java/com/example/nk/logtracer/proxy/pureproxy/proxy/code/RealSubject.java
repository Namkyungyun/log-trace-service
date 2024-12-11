package com.example.nk.logtracer.proxy.pureproxy.proxy.code;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class RealSubject implements Subject {
    @Override
    public String operation() {
        log.info("실제 구현 호출");
        sleep(1000);

        return "success"; //데이터를 조회하는데 1초가 걸린다.
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
