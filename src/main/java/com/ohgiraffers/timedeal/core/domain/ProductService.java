package com.ohgiraffers.timedeal.core.domain;

import com.ohgiraffers.timedeal.core.api.controller.v1.request.ProductRequest;
import com.ohgiraffers.timedeal.core.api.controller.v1.response.ProductResponse;
import com.ohgiraffers.timedeal.storage.ProductRepository;
// import com.ohgiraffers.timedeal.core.storage.PromotionRepository; // 추후 추가 시 import
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    // 1. 필드를 final로 선언하여 불변성 보장
    private final ProductRepository productRepository;
    // private final PromotionRepository promotionRepository;

    // 2. 생성자에 @Autowired 적용 (직접 주입)
    @Autowired
    public ProductService(ProductRepository productRepository /*, PromotionRepository promotionRepository */) {
        this.productRepository = productRepository;
        // this.promotionRepository = promotionRepository;
    }

    // --- 비즈니스 로직 (이전과 동일) ---

    // 1. 상품 추가
    @Transactional
    public Long create(ProductRequest request) throws IOException {
        String base64Image = convertToBase64(request.getImageFile());

        Product product = new Product(
                null,
                request.getName(),
                request.getDescription(),
                base64Image,
                request.getPrice()
        );

        return productRepository.save(product).getId();
    }

    // 2. 상품 수정
    @Transactional
    public void update(Long id, ProductRequest request) throws IOException {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상품입니다."));

        // TODO: PromotionRepository 생성 후 주석 해제
        // if (promotionRepository.existsByProductId(id)) {
        //     throw new IllegalStateException("프로모션에 등록된 상품은 수정할 수 없습니다.");
        // }

        String base64Image = null;
        if (request.getImageFile() != null && !request.getImageFile().isEmpty()) {
            base64Image = convertToBase64(request.getImageFile());
        }

        product.update(request.getName(), request.getDescription(), request.getPrice(), base64Image);
    }

    // 3. 상품 삭제
    @Transactional
    public void delete(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상품입니다."));

        // TODO: PromotionRepository 생성 후 주석 해제
        // if (promotionRepository.existsByProductId(id)) {
        //     throw new IllegalStateException("프로모션에 등록된 상품은 삭제할 수 없습니다.");
        // }

        productRepository.delete(product);
    }

    // 4. 조회 (전체)
    @Transactional(readOnly = true)
    public List<ProductResponse> findAll() {
        return productRepository.findAll().stream()
                .map(ProductResponse::new)
                .collect(Collectors.toList());
    }

    // 5. 조회 (단건)
    @Transactional(readOnly = true)
    public ProductResponse findById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상품입니다."));
        return new ProductResponse(product);
    }

    // [내부 메서드] 이미지 변환
    private String convertToBase64(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) {
            return "";
        }
        byte[] bytes = file.getBytes();
        return Base64.getEncoder().encodeToString(bytes);
    }
}