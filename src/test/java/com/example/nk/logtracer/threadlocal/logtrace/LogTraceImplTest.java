package com.example.nk.logtracer.threadlocal.logtrace;

import com.example.nk.logtracer.threadlocal.TraceStatus;
import org.junit.jupiter.api.Test;

class LogTraceImplTest {
    LogTrace logTrace = new LogTraceImpl();

    @Test
    public void begin_end() {
        TraceStatus status0 =  logTrace.begin("LogTraceImpl level0");
        TraceStatus status1 =  logTrace.begin("LogTraceImpl level1");
        TraceStatus status2 =  logTrace.begin("LogTraceImpl level2");

        logTrace.end(status2);
        logTrace.end(status1);
        logTrace.end(status0);
    }

    @Test
    public void begin_exception() {
        Runnable runnable1 = () -> {
            TraceStatus status0 =  logTrace.begin("LogTraceImpl level0");
            TraceStatus status1 =  logTrace.begin("LogTraceImpl level1");
            TraceStatus status2 =  logTrace.begin("LogTraceImpl level2");

            logTrace.exception(status2, new RuntimeException());
            logTrace.exception(status1, new IllegalStateException());
            logTrace.exception(status0, new Exception());
        };

        Runnable runnable2 = () -> {
            TraceStatus status0 =  logTrace.begin("LogTraceImpl level0");
            TraceStatus status1 =  logTrace.begin("LogTraceImpl level1");
            TraceStatus status2 =  logTrace.begin("LogTraceImpl level2");

            logTrace.exception(status2, new RuntimeException());
            logTrace.exception(status1, new IllegalStateException());
            logTrace.exception(status0, new Exception());
        };

        Thread thread1 = new Thread(runnable1);
        thread1.setName("thread 1");

        Thread thread2 = new Thread(runnable2);
        thread2.setName("thread 2");

        thread1.start();;
        thread2.start();

    }
}