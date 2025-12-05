package com.ohgiraffers.timedeal.core.domain;

import com.ohgiraffers.timedeal.core.support.key.TimedealKeys;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockService {
    private final StringRedisTemplate redisTemplate;

    public boolean decreaseStock(Long promotionId, int quantity) {
        String key = TimedealKeys.setPromotion(promotionId);

        String value = redisTemplate.opsForValue().get(key);
        if (value == null) return false;

        int stock = Integer.parseInt(value);

        if (stock < quantity) {
            return false;
        }

        redisTemplate.opsForValue().decrement(key, quantity);
        return true;
    }
}
