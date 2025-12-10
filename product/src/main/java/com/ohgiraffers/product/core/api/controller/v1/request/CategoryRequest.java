package com.ohgiraffers.product.core.api.controller.v1.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "카테고리 등록/수정 요청 DTO")
public record CategoryRequest(
        @Schema(description = "카테고리 이름", example = "전자제품")
        String name
) {}