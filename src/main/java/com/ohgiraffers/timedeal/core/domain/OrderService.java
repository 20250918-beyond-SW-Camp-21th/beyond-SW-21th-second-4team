package com.ohgiraffers.timedeal.core.domain;

import com.ohgiraffers.timedeal.core.api.controller.v1.request.OrderRequest;
import com.ohgiraffers.timedeal.core.api.controller.v1.response.OrderResponse;
import com.ohgiraffers.timedeal.core.enums.PromotionStatus;
import com.ohgiraffers.timedeal.core.support.error.CoreException;
import com.ohgiraffers.timedeal.core.support.error.ErrorType;
import com.ohgiraffers.timedeal.storage.OrderDetailRepository;
import com.ohgiraffers.timedeal.storage.OrderRepository;
import com.ohgiraffers.timedeal.storage.PromotionRepository;
import com.ohgiraffers.timedeal.storage.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final PromotionRepository promotionRepository;
    private final UserRepository userRepository;
    private final OrderDetailRepository orderDetailRepository;


    @Autowired
    public OrderService(
            OrderRepository orderRepository,
            PromotionRepository promotionRepository,
            UserRepository userRepository,
            OrderDetailRepository orderDetailRepository) {
        this.orderRepository = orderRepository;
        this.promotionRepository = promotionRepository;
        this.userRepository = userRepository;
        this.orderDetailRepository = orderDetailRepository;
    }

    @Transactional
    public OrderResponse createOrder(OrderRequest orderRequest) {

        // 요청 유효성 검증
        if(orderRequest.getPromotionId() == null) {
            throw new CoreException(ErrorType.DEFAULT_ERROR);
        }
        if(orderRequest.getQuantity() <= 0) {
            throw new CoreException(ErrorType.DEFAULT_ERROR);
        }

        // 상품 조회 + 비관적 락
        Promotion promotion = promotionRepository.findByIdWithPessimisticLock(orderRequest.getPromotionId())
                .orElseThrow(() -> new CoreException(ErrorType.DEFAULT_ERROR));

        // 유저 조회
        User user = userRepository.findById(orderRequest.getUserId())
                .orElseThrow(() -> new CoreException(ErrorType.DEFAULT_ERROR));

        // 프로모션 상태 체크
        if (promotion.getPromotionStatus() != PromotionStatus.ACTIVE) {
            throw new CoreException(ErrorType.DEFAULT_ERROR);
        }

        // 재고 체크
        if (promotion.getSoldQuantity() >= promotion.getTotalQuantity()) {
            throw new CoreException(ErrorType.DEFAULT_ERROR);
        }

        // 유저 잔액 체크
        if (user.getMoney() < promotion.getSalePrice()) {
            throw new CoreException(ErrorType.DEFAULT_ERROR);
        }

        // 구매 처리
        user.decreaseMoney(promotion.getSalePrice().longValue());     // 유저 잔액 차감
        promotion.increaseSoldQuantity();             // soldQuantity += 1

        // Order 생성
        Integer totalAmount = promotion.getSalePrice().intValue() * orderRequest.getQuantity();
        Order order = Order.create(user.getId(), totalAmount);
        orderRepository.save(order);

        // OrderDetail 생성
        OrderDetail orderDetail = OrderDetail.of(
                order.getId(),
                promotion.getId(),
                orderRequest.getQuantity(),
                promotion.getSalePrice().intValue()
        );
        orderDetailRepository.save(orderDetail);

        System.out.println(orderDetail);

        return new OrderResponse(orderDetail.getId(), 1); // quantity = 1
    }
}
