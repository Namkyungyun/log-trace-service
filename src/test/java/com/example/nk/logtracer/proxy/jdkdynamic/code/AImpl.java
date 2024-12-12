package com.example.nk.logtracer.proxy.jdkdynamic.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AImpl implements AInterface {

    @Override
    public String call() {
        log.info("A구현체 호출");
        return "A";
    }
}
