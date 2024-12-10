package com.example.nk.logtracer.threadlocal.concurrency_test;

import com.example.nk.logtracer.threadlocal.concurrency_test.code.ThreadLocalService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ThreadLocalServiceTest {
    final private ThreadLocalService threadLocalService = new ThreadLocalService();

    @Test
    void field() {
        log.info("main start");

        Runnable userA = () -> {
          threadLocalService.logic("userA");
        };

        Runnable userB = () -> {
          threadLocalService.logic("userB");
        };

        Thread threadA = new Thread(userA);
        threadA.setName("thread A");

        Thread threadB = new Thread(userB);
        threadB.setName("thread B");

        threadA.start();
        threadB.start();

        sleep(2000);
        log.info("main exit");
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
