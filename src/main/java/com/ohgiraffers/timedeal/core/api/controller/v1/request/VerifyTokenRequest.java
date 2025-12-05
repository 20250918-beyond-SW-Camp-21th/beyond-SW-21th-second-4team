package com.ohgiraffers.timedeal.core.api.controller.v1.request;

public record VerifyTokenRequest(
        Long userId,
        String token
) {

}
