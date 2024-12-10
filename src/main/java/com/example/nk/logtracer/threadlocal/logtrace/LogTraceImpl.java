package com.example.nk.logtracer.threadlocal.logtrace;

import com.example.nk.logtracer.threadlocal.TraceId;
import com.example.nk.logtracer.threadlocal.TraceStatus;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogTraceImpl implements LogTrace {
    private static final String START_PREFIX = "--->";
    private static final String COMPLETE_PREFIX = "<--";
    private static final String EX_PREFIX = "<X-";

    private final ThreadLocal<TraceId> traceIdHolder = new ThreadLocal<>(); // traceId 동기화, 동시성 이슈 발생 -> 해결을 위해 ThreadLocal 사용하여, 각 thread마다의 별도의 저장공간을 가지게 한다.


    @Override
    public TraceStatus begin(String message) {
        syncTraceId();
        TraceId traceId = traceIdHolder.get();
        Long startTimeMs = System.currentTimeMillis();


        log.info("[{}] {}{}", traceId.getId(), addSpace(START_PREFIX, traceId.getLevel()), message);

        return new TraceStatus(traceId, startTimeMs, message);
    }

    private void syncTraceId() {
        TraceId traceId = traceIdHolder.get();

        if(traceId == null) {
            traceIdHolder.set(new TraceId());
        } else {
            traceIdHolder.set(traceId.createNextId());
        }
    }

    @Override
    public void end(TraceStatus status) {
        complete(status, null);
    }

    @Override
    public void exception(TraceStatus status, Exception exception) {
        complete(status, exception);

    }

    private void complete(TraceStatus status, Exception exception) {
        Long stopTimeMs = System.currentTimeMillis();
        Long resultTimeMs = stopTimeMs - status.getStartTimeMs();

        TraceId traceId = status.getTraceId();

        if (exception == null) {
            log.info("[{}] {}{} time={}ms", traceId.getId(), addSpace(COMPLETE_PREFIX, traceId.getLevel()), status.getMessage(), resultTimeMs);
        } else {
            log.info("[{}] {}{} time={}ms ex={}", traceId.getId(), addSpace(EX_PREFIX, traceId.getLevel()), status.getMessage(), resultTimeMs, exception.toString());
        }
        
        releaseTraceId();
    }

    private void releaseTraceId() {
        TraceId traceId = traceIdHolder.get();

        if( traceId.isFirstLevel() ) {
            traceIdHolder .remove();
        } else {
            traceIdHolder.set(traceId.createPreviousId()); // level 하나씩 줄이기
        }
    }

    private static String addSpace(String prefix, int level) {
        StringBuilder sb = new StringBuilder();
        for( int i = 0; i < level; i++ ) {
            sb.append((i == level-1) ? "|" + prefix : "|  ");
        }

        return sb.toString();
    }
}
