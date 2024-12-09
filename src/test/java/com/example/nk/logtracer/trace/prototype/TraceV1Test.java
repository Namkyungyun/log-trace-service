package com.example.nk.logtracer.trace.prototype;

import com.example.nk.logtracer.trace.TraceStatus;
import org.junit.jupiter.api.Test;

class TraceV1Test {

    @Test
    public void begin_end() {
        TraceV1 trace = new TraceV1();

        TraceStatus traceStatus0 = trace.begin("trace v1");
        TraceStatus traceStatus1 = trace.beginSync(traceStatus0.getTraceId(), "trace v1 beginsync");

        trace.end(traceStatus1);
        trace.end(traceStatus0);
    }

    @Test
    public void begin_exception() {
        TraceV1 trace = new TraceV1();

        TraceStatus traceStatus0 = trace.begin("trace v1");
        TraceStatus traceStatus1 = trace.beginSync(traceStatus0.getTraceId(), "trace v1 beginsync");

        trace.exception(traceStatus1, new Exception());
        trace.exception(traceStatus0, new Exception());
    }


}