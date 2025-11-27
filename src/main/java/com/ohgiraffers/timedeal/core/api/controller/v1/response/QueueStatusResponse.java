package com.ohgiraffers.timedeal.core.api.controller.v1.response;

import com.ohgiraffers.timedeal.core.enums.QueueStatus;

import java.time.LocalTime;

public record QueueStatusResponse(
        Long position,
        Long waitTime,
        QueueStatus status
) {

}
