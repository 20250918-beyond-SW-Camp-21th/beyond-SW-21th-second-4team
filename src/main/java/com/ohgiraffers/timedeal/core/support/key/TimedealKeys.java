package com.ohgiraffers.timedeal.core.support.key;

/**
 *  Keys 규칙
 *  Redis에서는 콜론(:)으로 계층을 구분하는 것이 표준 관례 (서비스:도메인:식별자:용도)
 *
 *  예제
 *  timedeal:{dealId}:wait-queue      # 대기열
 *  timedeal:{dealId}:proceed-queue   # 처리 진행 중
 *  timedeal:{dealId}:stock           # 잔여 재고
 *  timedeal:{dealId}:completed       # 구매 완료 사용자
 */

public class TimedealKeys {
    private static final String PREFIX = "timedeal";

    public static String waitQueue(Long dealId) {
        return PREFIX + ":" + dealId + ":wait-queue";
    }

    public static String proceedQueue(Long dealId) {
        return PREFIX + ":" + dealId + ":proceed-queue";
    }
}
