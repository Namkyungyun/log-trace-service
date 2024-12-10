package com.example.nk.logtracer.threadlocal.concurrency_test.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadLocalService {
    private final ThreadLocal<String> nameStore = new ThreadLocal<>();

    public void logic(String name) {
        log.info("조회 name={} -> nameStore={}", name, nameStore.get());

        nameStore.set(name);
        sleep(1000);

        log.info("조회 nameStore={}", nameStore.get());
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
