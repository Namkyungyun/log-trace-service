package com.example.nk.logtracer.trace.logtrace;

import com.example.nk.logtracer.trace.TraceStatus;

public interface LogTrace {
    TraceStatus begin(String message);
    void end(TraceStatus status);
    void exception(TraceStatus status, Exception exception);

}
