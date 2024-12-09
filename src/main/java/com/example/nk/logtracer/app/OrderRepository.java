package com.example.nk.logtracer.app;

import com.example.nk.logtracer.trace.TraceStatus;
import com.example.nk.logtracer.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Slf4j
public class OrderRepository {

    private final LogTrace logTrace;

    public void healthCheck() {
        TraceStatus status = null;
        try {
            status = logTrace.begin("OrderRepository.healthCheck");
            log.info("========= health check");
            logTrace.end(status);
        } catch(Exception e) {
            logTrace.exception(status, e);
            throw e;
        }
    }

}
