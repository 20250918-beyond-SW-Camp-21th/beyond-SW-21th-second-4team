package com.ohgiraffers.timedeal.core.domain;

import com.ohgiraffers.timedeal.core.support.error.CoreException;
import com.ohgiraffers.timedeal.core.support.error.ErrorType;
import com.ohgiraffers.timedeal.core.support.key.TimedealKeys;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
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

    public void validProcessedQueue(Long userId) {
        ZSetOperations<String, String> zSetOps = redisTemplate.opsForZSet();

        Double expireAt = zSetOps.score(TimedealKeys.proceedQueue(userId), TimedealKeys.makeUser(userId));
        if (expireAt != null && expireAt > System.currentTimeMillis()) {
            throw new CoreException(ErrorType.DEFAULT_ERROR);
        }
    }

    public void deleteProcessedQueue(Long userId) {
        ZSetOperations<String, String> zSetOps = redisTemplate.opsForZSet();
        zSetOps.remove(TimedealKeys.proceedQueue(userId));
    }

    public void validCompleteQueue(Long promotionId, Long userId) {
        SetOperations<String, String> setOps = redisTemplate.opsForSet();
        setOps.isMember(TimedealKeys.completedQueue(promotionId));
    }

    public void addCompleteQueue(Long promotionId, Long userId) {
        SetOperations<String, String> setOps = redisTemplate.opsForSet();
        setOps.add(TimedealKeys.completedQueue(promotionId), userId.toString());
    }
}
