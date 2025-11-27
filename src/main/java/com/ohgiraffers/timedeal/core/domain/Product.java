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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 255, nullable = false)
    private String name;

    @Column(name = "description", length = 255, nullable = false)
    private String description;

    // String에 @Lob을 쓰면 DB에서는 LONGTEXT(MySQL)나 CLOB으로 생성됩니다.
    @Lob
    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    // 요청하신 대로 String 타입으로 설정했습니다.
    @Column(name = "price", nullable = false)
    private String price;

    public void update(String name, String description, String price, String imageUrl) {
        this.name = name;
        this.description = description;
        this.price = price;

        // 이미지가 비어있지 않을 때만 변경 (이미지를 수정 안 할 수도 있으니까요)
        if (imageUrl != null && !imageUrl.isEmpty()) {
            this.imageUrl = imageUrl;
        }
    }

}

