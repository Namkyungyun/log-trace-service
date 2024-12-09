package com.example.nk.logtracer.app;

import com.example.nk.logtracer.trace.TraceStatus;
import com.example.nk.logtracer.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final LogTrace logTrace;
    private final OrderService orderService;

    @RequestMapping(value = "/v1/health-check", method = RequestMethod.GET)
    public ResponseEntity<String> healthCheck() {
        TraceStatus traceStatus = null;

        try {
            traceStatus =  logTrace.begin("OrderController.healthCheck");

            orderService.healthCheck();

            logTrace.end(traceStatus);
            return ResponseEntity.ok("Hello World");

        } catch(Exception e) {
            logTrace.exception(traceStatus, e);
            throw new RuntimeException(e);
       }

    }

}
