package com.ohgiraffers.timedeal.core.api.controller.v1.response;

import com.ohgiraffers.timedeal.core.domain.Product;
import lombok.Getter;

@Getter
public class ProductResponse {
    private Long id;
    private String name;
    private String description;
    private int price;
    private String imageUrl; // Base64 문자열 반환

    public ProductResponse(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.imageUrl = product.getImageUrl();
    }
}