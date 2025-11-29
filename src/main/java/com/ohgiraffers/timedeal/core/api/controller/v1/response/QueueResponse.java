package com.ohgiraffers.timedeal.core.api.controller.v1.response;

import com.ohgiraffers.timedeal.core.enums.QueueStatus;

public record QueueResponse(
        Long position,
        Long waitTime,
        QueueStatus status
) {

}
