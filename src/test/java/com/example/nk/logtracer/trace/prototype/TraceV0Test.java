package com.example.nk.logtracer.trace.prototype;

import com.example.nk.logtracer.trace.TraceStatus;
import org.junit.jupiter.api.Test;

class TraceV0Test {

    @Test
    void begin_end() {
        TraceV0 traceV0 = new TraceV0();
        TraceStatus status = traceV0.begin("hello");
        traceV0.end(status);
    }

    @Test
    void begin_exception() {
        TraceV0 traceV0 = new TraceV0();
        TraceStatus status = traceV0.begin("hello");

        traceV0.exception(status, new IllegalStateException());
    }
}