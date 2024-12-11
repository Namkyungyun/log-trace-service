package com.example.nk.logtracer.config.v1_proxy.interface_proxy;

import com.example.nk.logtracer.app.proxy.v1.ProxyOrderRepositoryV1;
import com.example.nk.logtracer.threadlocal.TraceStatus;
import com.example.nk.logtracer.threadlocal.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderRepositoryInterfaceProxy implements ProxyOrderRepositoryV1 {

    private final LogTrace logTrace;
    private final ProxyOrderRepositoryV1 target;

    @Override
    public void save(String itemId) {
        TraceStatus status = null;
        try {
            status =logTrace.begin("OrderRepository.save()");
            target.save(itemId);
            logTrace.end(status);

        } catch(Exception e) {
            logTrace.exception(status, e);
            throw e;
        }
    }
}
