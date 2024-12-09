package com.example.nk.logtracer.trace;

import lombok.Getter;

@Getter
public class TraceStatus {

    final private TraceId traceId;
    final private Long startTimeMs;
    final private String message;


    public TraceStatus(TraceId traceId, Long startTimeMs, String message) {
        this.traceId = traceId;
        this.startTimeMs = startTimeMs;
        this.message = message;
    }
}
