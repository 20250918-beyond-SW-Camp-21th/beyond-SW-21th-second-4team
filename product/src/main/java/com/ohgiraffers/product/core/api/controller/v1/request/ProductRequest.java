package com.ohgiraffers.product.core.api.controller.v1.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "상품 등록 ")
public record ProductRequest(

        @Schema(description = "카테고리 ID", example = "1")
        Long categoryId,

        @Schema(description = "상품명", example = "노트북")
        String name,

        String description,
        Integer price,
        String imageUrl
) {

}