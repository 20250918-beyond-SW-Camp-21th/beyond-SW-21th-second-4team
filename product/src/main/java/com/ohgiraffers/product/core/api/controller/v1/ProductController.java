package com.ohgiraffers.product.core.api.controller.v1;

import com.ohgiraffers.common.support.response.ApiResult;
import com.ohgiraffers.product.core.api.controller.v1.request.ProductRequest;
import com.ohgiraffers.product.core.api.controller.v1.response.ProductResponse;
import com.ohgiraffers.product.core.domain.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "Product API", description = "상품 관련 API")
public class ProductController {
    private final ProductService productService;

    // 1. 상품 등록
    @PostMapping("/product")
    @Operation(summary = "상품 등록", description = "관리자가 새로운 상품을 등록합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "상품 등록 성공"),
            @ApiResponse(responseCode = "404", description = "카테고리를 찾을 수 없음"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청 값")
    })
    public ApiResult<?> createProduct(@AuthenticationPrincipal String adminId, @RequestBody ProductRequest request) {
        productService.createProduct(Long.parseLong(adminId), request);
        return ApiResult.success();
    }

    // 2. 상품 수정
    @PutMapping("/product/{productid}")
    @Operation(summary = "상품 수정", description = "관리자가 기존 상품을 수정합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "상품 수정 성공"),
            @ApiResponse(responseCode = "404", description = "상품 또는 카테고리를 찾을 수 없음"),
            @ApiResponse(responseCode = "403", description = "권한 없음")
    })
    public ApiResult<?> update(@AuthenticationPrincipal String adminId,@PathVariable Long productid, @RequestBody ProductRequest request) {
        productService.update(Long.parseLong(adminId), productid, request);
        return ApiResult.success();
    }

    // 3. 상품 삭제
    @DeleteMapping("/product/{productid}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "상품 삭제 성공"),
            @ApiResponse(responseCode = "404", description = "상품을 찾을 수 없음"),
            @ApiResponse(responseCode = "403", description = "권한 없음")
    })
    @Operation(summary = "상품 삭제", description = "관리자가 상품을 삭제합니다.")
    public ApiResult<?> delete(@AuthenticationPrincipal String adminId, @PathVariable Long productid) {
        productService.delete(Long.parseLong(adminId), productid);
        return ApiResult.success();
    }

    // 4. 단건 조회
    @GetMapping("/product/{productid}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "상품 조회 성공"),
            @ApiResponse(responseCode = "404", description = "상품 또는 카테고리를 찾을 수 없음")
    })
    @Operation(summary = "상품 조회", description = "상품 ID로 단건 조회합니다.")
    public ApiResult<ProductResponse> findById(@PathVariable Long productid) {
        return ApiResult.success(productService.findById(productid));
    }
}