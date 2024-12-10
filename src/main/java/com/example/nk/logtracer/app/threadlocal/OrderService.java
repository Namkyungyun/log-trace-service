package com.example.nk.logtracer.app.threadlocal;

import com.example.nk.logtracer.threadlocal.TraceStatus;
import com.example.nk.logtracer.threadlocal.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final LogTrace logTrace;
    private final OrderRepository orderRepository;

    public void healthCheck() {
        TraceStatus status = null;

        try {
            status = logTrace.begin("OrderService.healthCheck");
            orderRepository.healthCheck();

            logTrace.end(status);

        } catch(Exception e) {
            logTrace.exception(status, e);
            throw e;
        }
    }

}
