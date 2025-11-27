package com.ohgiraffers.timedeal.core.domain;

import com.ohgiraffers.timedeal.core.api.controller.v1.response.QueueResponse;
import com.ohgiraffers.timedeal.core.api.controller.v1.response.QueueStatusResponse;
import com.ohgiraffers.timedeal.core.enums.QueueStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class QueueService {
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public QueueService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public QueueResponse enterQueue(Long dealId, Long userId) {
        ZSetOperations<String, Object> queue = redisTemplate.opsForZSet();

        // key값을 dealId로 구분
        String queueKey = "queue:" + dealId;

        // 현재 시간을 UTC기준 Millisecond로 변환하여 score로 사용
        long score = System.currentTimeMillis();
        
        // 신규유저는 생성되고 기존유저는 업데이트됨
        queue.add(queueKey, userId, score);

        // User의 Queue상태를 저장 (임의로 대기시간 정함)
        Long position = queue.rank(queueKey, userId);
        Long waitTime = (long) ((position / 10) * 10);
        QueueStatusResponse statusResponse = new QueueStatusResponse(
                position,
                waitTime,
                QueueStatus.WAITING
        );

        // UUID로 Token값 생성
        String token = UUID.randomUUID().toString();

        return new QueueResponse(token, statusResponse);
    }
}
