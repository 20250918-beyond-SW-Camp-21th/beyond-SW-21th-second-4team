package com.ohgiraffers.timedeal.core.api.controller.v1.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter @Setter
public class ProductRequest {
    private String name;
    private String description;
    private int price;
    private String imageUrl; // 파일 업로드용
}