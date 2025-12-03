package com.ohgiraffers.timedeal.core.api.controller.v1.request;

import com.ohgiraffers.timedeal.core.support.error.CoreException;
import com.ohgiraffers.timedeal.core.support.error.ErrorType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {

    @NotNull
    private Long promotionId;

    @NotNull
    @Positive
    private Integer quantity;

    @NotNull
    private Long userId;

    public void validate() {
        if (promotionId == null) {
            throw new CoreException(ErrorType.DEFAULT_ERROR);
        }
        if (quantity <= 0) {
            throw new CoreException(ErrorType.DEFAULT_ERROR);
        }
    }
}

