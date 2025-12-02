package com.ohgiraffers.timedeal.core.api.controller.scheduler;

import com.ohgiraffers.timedeal.core.domain.PromotionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PromotionScheduler {
    private final StringRedisTemplate stringRedisTemplate;
    PromotionService promotionService;

    @Scheduled(fixedRate = 60000)
    public void promotion() {

        Long timedealId = 1L;
    }
}
