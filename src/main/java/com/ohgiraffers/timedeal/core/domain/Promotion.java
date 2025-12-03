package com.ohgiraffers.timedeal.core.domain;

import com.ohgiraffers.timedeal.core.enums.EntityStatus;
import com.ohgiraffers.timedeal.core.enums.PromotionStatus;
import com.ohgiraffers.timedeal.core.support.error.CoreException;
import com.ohgiraffers.timedeal.core.support.error.ErrorType;
import com.ohgiraffers.timedeal.storage.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "promotions")
public class Promotion extends BaseEntity {

    @Column(name = "admin_id" ,  nullable = false)
    private Long adminId;

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "sale_price")
    private Double salePrice;

    @Column(name = "discount_rate")
    private Double discountRate;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "total_quantity")
    private Integer totalQuantity;

    @Column(name = "sold_quantity")
    private Integer soldQuantity;

    @Column(name = "promotion_status")
    @Enumerated(EnumType.STRING)
    private PromotionStatus promotionStatus = PromotionStatus.ACTIVE;

    public void increaseSoldQuantity() {
        if(this.soldQuantity > this.totalQuantity) {
            throw new CoreException(ErrorType.DEFAULT_ERROR);
        }
        this.soldQuantity += 1;
    }
}
