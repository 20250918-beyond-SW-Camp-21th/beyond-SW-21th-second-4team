package com.ohgiraffers.timedeal.core.api.controller.scheduler;

import com.ohgiraffers.timedeal.core.api.controller.v1.response.PromotionResponse;
import com.ohgiraffers.timedeal.core.api.controller.v1.response.RedisPromotionResponse;
import com.ohgiraffers.timedeal.core.domain.Promotion;
import com.ohgiraffers.timedeal.core.domain.PromotionService;
import com.ohgiraffers.timedeal.core.enums.PromotionStatus;
import com.ohgiraffers.timedeal.core.support.key.TimedealKeys;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

import static com.ohgiraffers.timedeal.core.enums.PromotionStatus.*;

@Component
@RequiredArgsConstructor
public class PromotionScheduler {
    private final StringRedisTemplate stringRedisTemplate;
    private final PromotionService promotionService;
    @Scheduled(fixedRate = 60000)
    public void updateSchedule() {
        List<Promotion> checkSchedule = promotionService.updateStatus();
        for(Promotion promotion : checkSchedule){
            if(promotion.getPromotionStatus().equals(SCHEDULER) && promotion.getStartTime().isAfter(LocalDateTime.now())){
                promotion.changeStatus(ACTIVE);
            } else if (promotion.getPromotionStatus().equals(ACTIVE) && promotion.getEndTime().isAfter(LocalDateTime.now())) {
                promotion.changeStatus(ENDED);
            }
        }
    }


    @Scheduled(fixedRate = 60000)
    public void checkpromotion() {
        List<RedisPromotionResponse> schedulePromotion =  promotionService.returnSchedule(SCHEDULER);
        List<RedisPromotionResponse> activePromotion = promotionService.returnActive(SCHEDULER);
        for(RedisPromotionResponse promotion : schedulePromotion){
            String key = TimedealKeys.setPromotion(promotion.timedealId());
            Integer value = promotion.totalQuantity();
            stringRedisTemplate.opsForValue().set(key, String.valueOf(value));
        }
        for(RedisPromotionResponse promotion : activePromotion){
            String key = TimedealKeys.setPromotion(promotion.timedealId());
            stringRedisTemplate.delete(key);
        }
    }


}
