package com.ohgiraffers.product.core.api.controller.v1.response;

import com.ohgiraffers.product.core.domain.Product;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Product Response DTO")
public record ProductResponse(
        @Schema(description = "상품 ID", example = "1")
        Long id,

        @Schema(description = "회사명", example = "Ohgiraffers Corp")
        String companyName,

        @Schema(description = "카테고리명", example = "전자제품")
        String categoryName,

        @Schema(description = "상품명", example = "노트북")
        String name,

        @Schema(description = "상품 설명", example = "최신형 고성능 노트북")
        String description,

        @Schema(description = "상품 가격", example = "1500000")
        Integer price,

        @Schema(description = "상품 이미지 URL", example = "https://images.unsplash.com/photo-1517336714731-489689fd1ca8")
        String imageUrl
) {}
