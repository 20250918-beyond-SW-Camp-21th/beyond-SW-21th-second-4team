package com.ohgiraffers.timedeal.core.api.controller.v1.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {

    private Long promotionId;
    private Integer quantity;
    private Long userId;
}
