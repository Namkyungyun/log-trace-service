package com.example.nk.logtracer.config.v1_proxy.interface_proxy;

import com.example.nk.logtracer.app.proxy.v1.ProxyOrderServiceV1;
import com.example.nk.logtracer.threadlocal.TraceStatus;
import com.example.nk.logtracer.threadlocal.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderServiceInterfaceProxy implements ProxyOrderServiceV1 {

    private final LogTrace logTrace;
    private final ProxyOrderServiceV1 target;

    @Override
    public void orderItem(String itemId) {
        TraceStatus status = null;
        try {
            status = logTrace.begin("ProxyOrderServiceV1.orderItem");
            target.orderItem(itemId);
            logTrace.end(status);
        } catch(Exception e) {
            logTrace.exception(status, e);
            throw e;
        }

    }
}
