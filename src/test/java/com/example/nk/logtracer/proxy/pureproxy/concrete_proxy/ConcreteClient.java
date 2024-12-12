package com.example.nk.logtracer.proxy.pureproxy.concrete_proxy;

import lombok.extern.slf4j.Slf4j;

public class ConcreteClient {

    private final ConcreteLogic concreteLogic;


    public ConcreteClient(ConcreteLogic concreteLogic) {
        this.concreteLogic = concreteLogic;
    }

    void execute() {
        concreteLogic.operation();
    }
}
