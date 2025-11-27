package com.ohgiraffers.timedeal.core.domain;

import com.ohgiraffers.timedeal.core.api.controller.v1.response.QueueResponse;
import com.ohgiraffers.timedeal.core.enums.QueueStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@DisplayName("대기열 서비스 단위 테스트")
@ExtendWith(MockitoExtension.class)
class QueueServiceTests {

    @Mock
    RedisTemplate<String, Object> redisTemplate;

    @Mock
    ZSetOperations<String, Object> zsetOperations;

    @InjectMocks
    QueueService queueService;

    @Test
    @DisplayName("첫 진입 시 순번 0을 반환")
    public void firstEnterQueue() {
        // given
        Long dealId = 1L;
        Long userId = 100L;

        given(redisTemplate.opsForZSet()).willReturn(zsetOperations);
        given(zsetOperations.add(anyString(), any(), anyDouble())).willReturn(true);
        given(zsetOperations.rank(anyString(), any())).willReturn(0L);

        // when
        QueueResponse response = queueService.enterQueue(dealId, userId);

        // then
        assertThat(response).isNotNull();
        assertThat(response.token()).isNotNull();
        assertThat(response.queueStatusResponse().position()).isEqualTo(0L);
        assertThat(response.queueStatusResponse().waitTime()).isEqualTo(0L);
        assertThat(response.queueStatusResponse().status()).isEqualTo(QueueStatus.WAITING);

        verify(zsetOperations).add(eq("queue:1"), eq(userId), anyDouble());
        verify(zsetOperations).rank("queue:1", userId);
    }

    @Test
    @DisplayName("10번째 진입 시 대기시간 100초를 반환한다")
    public void lateEnterQueue() {
        // given
        Long dealId = 1L;
        Long userId = 100L;

        given(redisTemplate.opsForZSet()).willReturn(zsetOperations);
        given(zsetOperations.add(anyString(), any(), anyDouble())).willReturn(true);
        given(zsetOperations.rank(anyString(), any())).willReturn(10L);

        // when
        QueueResponse response = queueService.enterQueue(dealId, userId);

        // then
        assertThat(response.queueStatusResponse().position()).isEqualTo(10L);
        assertThat(response.queueStatusResponse().waitTime()).isEqualTo(10L);
    }

    @Test
    @DisplayName("중복 진입 시 기존 score 업데이트")
    void enterQueueDuplicate() {
        // given
        Long dealId = 1L;
        Long userId = 100L;

        given(redisTemplate.opsForZSet()).willReturn(zsetOperations);
        given(zsetOperations.add(anyString(), any(), anyDouble())).willReturn(false);
        given(zsetOperations.rank(anyString(), any())).willReturn(3L);

        // when
        QueueResponse response = queueService.enterQueue(dealId, userId);

        // then
        assertThat(response.queueStatusResponse().position()).isEqualTo(3L);
        verify(zsetOperations).add(eq("queue:1"), eq(userId), anyDouble());
    }

    @Test
    @DisplayName("dealId별로 독립적인 대기열이 생성")
    void separateQueueByDealId() {
        // given
        Long dealId1 = 1L;
        Long dealId2 = 2L;
        Long userId = 100L;

        given(redisTemplate.opsForZSet()).willReturn(zsetOperations);
        given(zsetOperations.add(anyString(), any(), anyDouble())).willReturn(true);
        given(zsetOperations.rank(anyString(), any())).willReturn(0L);

        // when
        queueService.enterQueue(dealId1, userId);
        queueService.enterQueue(dealId2, userId);

        // then
        verify(zsetOperations).add(eq("queue:1"), eq(userId), anyDouble());
        verify(zsetOperations).add(eq("queue:2"), eq(userId), anyDouble());
    }
}