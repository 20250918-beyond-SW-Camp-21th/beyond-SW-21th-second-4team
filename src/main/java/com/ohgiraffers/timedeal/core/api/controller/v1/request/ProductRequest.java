package com.ohgiraffers.timedeal.core.api.controller.v1.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter @Setter
public class ProductRequest {
    private String name;
    private String description;
    private String price; // Entity 타입에 맞춰 String
    private MultipartFile imageFile; // 파일 업로드용
}