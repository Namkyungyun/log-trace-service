package com.example.nk.logtracer.threadlocal.concurrency_test;

import com.example.nk.logtracer.threadlocal.concurrency_test.code.FieldService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class FieldServiceTest {

    private FieldService fieldService = new FieldService();

    @Test
    void field() {
        log.info("main start");

        Runnable userA = () -> {
            fieldService.logic("userA");
        };

        Runnable userB = () -> {
            fieldService.logic("userB");
        };

        Thread threadA = new Thread(userA);
        threadA.setName("thread A");

        Thread threadB = new Thread(userB);
        threadB.setName("thread B");

        threadA.start();
//        sleep(2000); // 동시성 문제 발생 X
        sleep(100); // 동시성 문제 발생 O -> 이를 해결하기 위해 ThreadLocal 사용이 필요

        threadB.start();

        sleep(2000); // 메인 종료 늦추기
        log.info("main exist");
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
