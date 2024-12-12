package com.example.nk.logtracer.proxy.jdkdynamic.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BImpl implements BInterface {

    @Override
    public String call() {
        log.info("B구현체 호출");
        return "B";
    }
}
