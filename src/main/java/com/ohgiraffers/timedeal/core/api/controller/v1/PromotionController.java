package com.ohgiraffers.timedeal.core.api.controller.v1;

import com.ohgiraffers.timedeal.core.api.controller.v1.request.PromotionRequest;
import com.ohgiraffers.timedeal.core.api.controller.v1.response.PromotionResponse;
import com.ohgiraffers.timedeal.core.domain.Promotion;
import com.ohgiraffers.timedeal.core.domain.PromotionService;
import com.ohgiraffers.timedeal.core.enums.PromotionStatus;
import com.ohgiraffers.timedeal.core.support.response.ApiResult;
import com.ohgiraffers.timedeal.storage.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.naming.Name;
import java.util.List;

@RestController
public class PromotionController {
    private final PromotionRepository promotionRepository;
    private PromotionService promotionService;



    @Autowired
    public PromotionController(PromotionService promotionService, PromotionRepository promotionRepository) {
        this.promotionService = promotionService;
        this.promotionRepository = promotionRepository;
    }

    @PostMapping("/api/v1/promotions")
    public ApiResult<?> save(@RequestBody PromotionRequest promotionRequest) {
        promotionService.promotionSave(promotionRequest);
        return ApiResult.success();
    }
    @PutMapping("/api/v1/promotions/{id}")
    public ApiResult<?> update(
            @PathVariable long id,
            @RequestBody PromotionRequest promotionRequest) {
        promotionService.promotionUpdateById(id,promotionRequest);
        return ApiResult.success();
    }
    @PutMapping("/api/v1/promotions")
    public ApiResult<?> updateStatus(@RequestParam(name = "id") Long id) {
        promotionService.promotionUpdateStatusById(id);
        return ApiResult.success();
    }

    @DeleteMapping("/api/v1/promotions/{id}")
    public ApiResult<?> deleteById(@PathVariable Long id) {
        promotionService.deletePromotion(id);
        return ApiResult.success();
    }
    @GetMapping("api/v1/promotions")
    public ApiResult<List<PromotionResponse>> getAllPromotion() {
        List<PromotionResponse> result = promotionService.findAll();
        return ApiResult.success(result);
    }
    @GetMapping("api/v1/promtions/{promotionStatus}")
    public ApiResult<List<PromotionResponse>> getPromotionsStatusAll(
            @PathVariable PromotionStatus promotionStatus)
    {
        return ApiResult.success(promotionService.getPromotionsByStatus(promotionStatus));
    }
}
