package com.ohgiraffers.queue.core.storage;

import java.util.Optional;
import java.util.Set;

public interface QueueRepository {

    // Reader
    boolean isUserValidInWaitQueue(Long timedealId, Long userId);
    boolean isUserValidInProceedQueue(Long timedealId, Long userId);

    Set<String> getAllWaitQueue(Long timedealId);

    Optional<Long> getWaitQueueCount(Long timedealId);
    Optional<Long> getProceedQueueCount(Long timedealId);
    Optional<Long> getWaitQueuePosition(Long timedealId, Long userId);
    Optional<Double> getProceedQueueExpire(Long timedealId, Long userId);

    // Writer
    void addWaitQueue(Long timedealId, Long userId);
    void addProceedQueue(Long timedealId, Long userId, long expireAt);

    boolean removeWaitQueue(Long timedealId, Long userId);
    Set<String> removeRangeWaitQueue(Long timedealId, Long count);
    boolean removeProceedQueue(Long timedealId, Long userId);
    void removeRangeProceedQueue(Long timedealId, Long now);
}
