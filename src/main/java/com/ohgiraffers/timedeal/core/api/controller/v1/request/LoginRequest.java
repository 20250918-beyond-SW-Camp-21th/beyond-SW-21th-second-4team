package com.ohgiraffers.timedeal.core.api.controller.v1.request;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginRequest {
    private final String email;


    private final String password;
}
