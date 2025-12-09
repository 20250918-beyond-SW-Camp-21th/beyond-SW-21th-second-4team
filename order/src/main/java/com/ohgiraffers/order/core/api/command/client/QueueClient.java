package com.ohgiraffers.order.core.api.command.client;

import com.ohgiraffers.common.support.response.ApiResult;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient
public interface QueueClient {

    ApiResult<Object> verifyQueue(Long timedealId, Long userId);
}
