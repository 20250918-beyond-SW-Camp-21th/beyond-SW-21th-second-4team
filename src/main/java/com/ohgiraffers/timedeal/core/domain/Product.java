package com.ohgiraffers.timedeal.core.domain;

import com.ohgiraffers.timedeal.storage.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class Product extends BaseEntity {

    @Column(name = "name", length = 255, nullable = false)
    private String name;

    @Column(name = "description", length = 255, nullable = false)
    private String description;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @Column(name = "price", nullable = false)
    private int price;

    public void update(String name, String description, int price, String imageUrl) {
        this.name = name;
        this.description = description;
        this.price = price;

        // 이미지가 비어있지 않을 때만 변경 (이미지를 수정 안 할 수도 있으니까요)
        if (imageUrl != null && !imageUrl.isEmpty()) {
            this.imageUrl = imageUrl;
        }
    }

}

