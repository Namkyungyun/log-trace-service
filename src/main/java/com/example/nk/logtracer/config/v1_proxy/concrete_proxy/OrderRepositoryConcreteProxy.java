package com.example.nk.logtracer.config.v1_proxy.concrete_proxy;

import com.example.nk.logtracer.app.proxy.v2.ProxyOrderRepositoryV2;
import com.example.nk.logtracer.threadlocal.TraceStatus;
import com.example.nk.logtracer.threadlocal.logtrace.LogTrace;


public class OrderRepositoryConcreteProxy extends ProxyOrderRepositoryV2 {

    private final ProxyOrderRepositoryV2 target;
    private final LogTrace logTrace;

    public OrderRepositoryConcreteProxy(ProxyOrderRepositoryV2 target,LogTrace logTrace) {
        this.target = target;
        this.logTrace = logTrace;
    }

    @Override
    public void save(String itemId) {
        TraceStatus status = null;
        try {
            status = logTrace.begin("OrderRepositoryConcreteProxy.save");
            target.save(itemId);
            logTrace.end(status);

        }catch(Exception e) {
            logTrace.exception(status, e);
            throw e;
        }
    }
}
