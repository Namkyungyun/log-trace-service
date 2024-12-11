package com.example.nk.logtracer.config.v1_proxy.interface_proxy;

import com.example.nk.logtracer.app.proxy.v1.ProxyOrderControllerV1;
import com.example.nk.logtracer.threadlocal.TraceStatus;
import com.example.nk.logtracer.threadlocal.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class OrderControllerInterfaceProxy implements ProxyOrderControllerV1 {

    private final LogTrace logTrace;
    private final ProxyOrderControllerV1 target;

    @Override
    public String request(String itemId) {
        TraceStatus status = null;
        try {
            status = logTrace.begin("ProxyOrderControllerV1,request");
            target.request(itemId);
            logTrace.end(status);
            return "ok";

        } catch(Exception e) {
            logTrace.exception(status, e);
            throw e;
        }
    }

    @Override
    public String noLog() {
        return "";
    }
}
