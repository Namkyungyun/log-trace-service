package com.example.nk.logtracer.threadlocal.concurrency_test.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FieldService {

    private String nameStore; // 동시성이 발생하는 케이스

    public String logic(String name) {
        log.info("조회 name={} -> nameStore={}", name, nameStore);

        nameStore = name;
        sleep(1000);

        log.info("조회 nameStore={}", nameStore);
        return nameStore;
    }


    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
