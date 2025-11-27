package com.ohgiraffers.timedeal.core.api.controller.v1;

import com.ohgiraffers.timedeal.core.api.controller.v1.response.QueueResponse;
import com.ohgiraffers.timedeal.core.domain.QueueService;
import com.ohgiraffers.timedeal.core.support.response.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QueueController {
    private final QueueService queueService;

    @Autowired
    public QueueController(QueueService queueService) {
        this.queueService = queueService;
    }

    @PostMapping("/api/v1/queue")
    public ApiResult<QueueResponse> enterQueue(
            @RequestParam(value = "dealId") Long dealId,
            @RequestParam(value = "userId") Long userId
    ) {
        return ApiResult.success(queueService.enterQueue(dealId, userId));
    }
}
