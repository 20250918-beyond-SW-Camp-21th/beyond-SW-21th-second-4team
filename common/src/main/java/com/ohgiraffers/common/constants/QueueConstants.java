package com.ohgiraffers.common.constants;

public final class QueueConstants {

    private QueueConstants() {

    }

    // 대기열 설정
    public static final long    QUEUE_SCHEDULER_INTERVAL_MILLIS = (10 * 1000L);
    public static final long    PROCEED_QUEUE_TTL_MILLIS = (5 * 60 * 1000L);
    public static final long    DEFAULT_PROCESSING_TIME_MILLIS = (3 * 1000L);

    public static final int     PROCEED_QUEUE_CAPACITY = 100;

    // SSE 설정
    public static final long    SSE_TIMEOUT_MILLIS = (5 * 60 * 1000L);
    public static final long    SSE_HEARTBEAT_INTERVAL_MILLIS = (30 * 1000L);
}