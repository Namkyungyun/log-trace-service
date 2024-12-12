package com.example.nk.logtracer.config.v1_proxy.concrete_proxy;

import com.example.nk.logtracer.app.proxy.v2.ProxyOrderServiceV2;
import com.example.nk.logtracer.threadlocal.TraceStatus;
import com.example.nk.logtracer.threadlocal.logtrace.LogTrace;

public class OrderServiceConcreteProxy extends ProxyOrderServiceV2 {

    private final ProxyOrderServiceV2 target;
    private final LogTrace logTrace;

    public OrderServiceConcreteProxy(ProxyOrderServiceV2 target, LogTrace logTrace) {
        super(null);
        this.target = target;
        this.logTrace = logTrace;
    }


    @Override
    public void orderItem(String itemId) {
        TraceStatus status = null;
        try {
            status = logTrace.begin("OrderServiceConcreteProxy.orderItem");
            target.orderItem(itemId);
            logTrace.end(status);

        } catch(Exception e) {
            logTrace.exception(status, e);
            throw e;
        }

    }


}
