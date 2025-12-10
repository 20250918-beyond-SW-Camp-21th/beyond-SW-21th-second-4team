package com.ohgiraffers.account.core.api.controller.v1.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

public record AdminLoginRequest(
        @NotBlank
        @Pattern(
                regexp = ".*@.*",
                message = "이메일에는 @ 문자가 반드시 포함되어야 합니다."
        )
        @Schema(description = "email값 ", example = "admin1@example.com",requiredMode = REQUIRED)
        String email,

        @NotBlank
        @Schema(description = "비밀번호 값", example = "Pass1234!",requiredMode = REQUIRED)
        String password
) {
}
