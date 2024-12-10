package com.example.nk.logtracer.threadlocal.logtrace;

import com.example.nk.logtracer.threadlocal.TraceStatus;

public interface LogTrace {
    TraceStatus begin(String message);
    void end(TraceStatus status);
    void exception(TraceStatus status, Exception exception);

}
