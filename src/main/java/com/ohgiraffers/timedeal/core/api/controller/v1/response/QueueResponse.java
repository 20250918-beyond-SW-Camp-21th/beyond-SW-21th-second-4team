package com.ohgiraffers.timedeal.core.api.controller.v1.response;

import java.time.LocalTime;

public record QueueResponse(
        String token,
        QueueStatusResponse queueStatusResponse
) {

}
