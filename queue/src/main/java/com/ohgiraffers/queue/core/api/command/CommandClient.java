package com.ohgiraffers.queue.core.api.command;

import com.ohgiraffers.common.support.response.ApiResult;
import com.ohgiraffers.common.support.response.ResultType;
import com.ohgiraffers.queue.core.api.controller.v1.response.PromotionListResponse;
import com.ohgiraffers.queue.core.api.controller.v1.response.PromotionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CommandClient {
    private final PromotionClient promotionClient;

    public List<Long> getActivePromotions() {
        ApiResult<PromotionListResponse> response = promotionClient.getPromotionsStatusAll("ACTIVE");
        if (response.getResult() == ResultType.ERROR) {
            return null;
        }

        return response.getData().promotions().stream()
                .map(PromotionResponse::id)
                .toList();
    }

    public boolean isValidPromotion(Long timedealId) {
        ApiResult<PromotionResponse> promotionResponse = promotionClient.findPromotionById(timedealId);
        return promotionResponse.getResult() == ResultType.SUCCESS;
    }
}
