package com.ohgiraffers.product.core.api.controller.v1.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Product List Response DTO")
public record ProductListResponse(
        @Schema(description = "상품 목록")
        List<ProductResponse> products
) {

}