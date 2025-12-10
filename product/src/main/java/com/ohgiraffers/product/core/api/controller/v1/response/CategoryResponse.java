package com.ohgiraffers.product.core.api.controller.v1.response;

import com.ohgiraffers.product.core.domain.Category;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "카테고리 응답 DTO")
public record CategoryResponse(
        @Schema(description = "카테고리 ID", example = "5")
        Long id,

        @Schema(description = "카테고리 이름", example = "전자제품")
        String name
) {
    public static CategoryResponse from(Category category) {
        return new CategoryResponse(category.getId(), category.getName());
    }
}