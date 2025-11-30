package com.ohgiraffers.timedeal.core.domain;

import com.ohgiraffers.timedeal.core.api.controller.v1.request.ProductRequest;
import com.ohgiraffers.timedeal.core.api.controller.v1.response.ProductResponse;
import com.ohgiraffers.timedeal.storage.AdminRepository;
import com.ohgiraffers.timedeal.storage.ProductRepository;
import com.ohgiraffers.timedeal.core.support.error.CoreException;
import com.ohgiraffers.timedeal.core.support.error.ErrorType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final AdminRepository adminRepository;

    public ProductService(ProductRepository productRepository, AdminRepository adminRepository) {
        this.productRepository = productRepository;
        this.adminRepository = adminRepository;
    }

    // 상품 등록
    @Transactional
    public void createProduct(ProductRequest request) {
        Admin admin = adminRepository.findById(request.getAdminId())
                .orElseThrow(() -> new CoreException(ErrorType.DEFAULT_ERROR));

        Product product = new Product(
                request.getName(),
                request.getDescription(),
                request.getImageUrl(),
                request.getPrice(),
                request.getCategory(),
                admin
        );

        productRepository.save(product);
    }

    // 상품 수정
    @Transactional
    public void update(Long productId, ProductRequest request) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new CoreException(ErrorType.DEFAULT_ERROR));

        Admin admin = adminRepository.findById(request.getAdminId())
                .orElseThrow(() -> new CoreException(ErrorType.DEFAULT_ERROR));

        product.update(request.getName(), request.getDescription(),
                request.getPrice(), request.getImageUrl(),
                request.getCategory(), admin);

        productRepository.save(product);
    }

    // 상품 삭제
    @Transactional
    public void delete(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new CoreException(ErrorType.DEFAULT_ERROR));
        productRepository.delete(product);
    }

    // 전체 조회
    @Transactional(readOnly = true)
    public List<ProductResponse> findAll() {
        return productRepository.findAll().stream()
                .map(ProductResponse::from)
                .collect(Collectors.toList());
    }

    // 단건 조회
    @Transactional(readOnly = true)
    public ProductResponse findById(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new CoreException(ErrorType.DEFAULT_ERROR));
        return ProductResponse.from(product);
    }

    // 관리자별 조회 (AdminController에서 사용)
    @Transactional(readOnly = true)
    public List<ProductResponse> findByAdminId(Long adminId) {
        Admin admin = adminRepository.findById(adminId)
                .orElseThrow(() -> new CoreException(ErrorType.DEFAULT_ERROR));
        return admin.getProducts().stream()
                .map(ProductResponse::from)
                .collect(Collectors.toList());
    }

    // 관리자 권한으로 수정
    @Transactional
    public ProductResponse updateProductByAdmin(Long adminId, Long productId, ProductRequest request) {
        Admin admin = adminRepository.findById(adminId)
                .orElseThrow(() -> new CoreException(ErrorType.DEFAULT_ERROR));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new CoreException(ErrorType.DEFAULT_ERROR));

        if (!product.getAdmin().getId().equals(admin.getId())) {
            throw new CoreException(ErrorType.DEFAULT_ARGUMENT_NOT_VALID); // 권한 문제
        }

        product.update(request.getName(), request.getDescription(), request.getPrice(),
                request.getImageUrl(), request.getCategory(), admin);

        return ProductResponse.from(productRepository.save(product));
    }

    // 관리자 권한으로 삭제
    @Transactional
    public void deleteProductByAdmin(Long adminId, Long productId) {
        Admin admin = adminRepository.findById(adminId)
                .orElseThrow(() -> new CoreException(ErrorType.DEFAULT_ERROR));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new CoreException(ErrorType.DEFAULT_ERROR));

        if (!product.getAdmin().getId().equals(admin.getId())) {
            throw new CoreException(ErrorType.DEFAULT_ARGUMENT_NOT_VALID); // 권한 문제
        }

        productRepository.delete(product);
    }
}