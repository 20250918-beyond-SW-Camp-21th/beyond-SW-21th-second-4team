package com.ohgiraffers.product.core.api.controller.v1.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "상품 등록 ")
public record ProductRequest(

        @Schema(description = "카테고리 ID", example = "1")
        Long categoryId,

        @Schema(description = "상품명", example = "노트북")
        String name,

        @Schema(description = "상품설명", example = "최신형 고성능 노트북")
        String description,

        @Schema(description = "상품가격", example = "1500000")
        Integer price,

        @Schema(description = "상품이미지 URL", example = "https://example.com/images/laptop.png")
        String imageUrl
) {

}