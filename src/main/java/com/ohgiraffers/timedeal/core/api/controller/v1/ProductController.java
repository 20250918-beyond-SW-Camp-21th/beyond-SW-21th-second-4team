package com.ohgiraffers.timedeal.core.api.controller.v1;

import com.ohgiraffers.timedeal.core.api.controller.v1.request.ProductRequest;
import com.ohgiraffers.timedeal.core.api.controller.v1.response.ProductResponse;
import com.ohgiraffers.timedeal.core.domain.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    // 1. 상품 등록 (파일 업로드 포함)
    @PostMapping
    public ResponseEntity<Long> create(@ModelAttribute ProductRequest request) throws IOException {
        // @ModelAttribute: multipart/form-data 요청을 처리하기 위해 사용합니다.
        return ResponseEntity.ok(productService.create(request));
    }

    // 2. 상품 수정
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @ModelAttribute ProductRequest request) throws IOException {
        productService.update(id, request);
        return ResponseEntity.ok().build();
    }

    // 3. 상품 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.ok().build();
    }

    // 4. 전체 조회
    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAll() {
        return ResponseEntity.ok(productService.findAll());
    }

    // 5. 단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.findById(id));
    }
}