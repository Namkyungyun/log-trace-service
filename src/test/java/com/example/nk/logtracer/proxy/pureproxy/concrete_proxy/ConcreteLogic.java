package com.example.nk.logtracer.proxy.pureproxy.concrete_proxy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConcreteLogic {

    public String operation() {
        log.info("ConcreteLogic 실행");
        return "data";
    }

}
