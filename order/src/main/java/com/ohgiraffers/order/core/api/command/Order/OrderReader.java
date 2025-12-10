package com.ohgiraffers.order.core.api.command.Order;

import com.ohgiraffers.common.support.error.CoreException;
import com.ohgiraffers.common.support.error.ErrorType;
import com.ohgiraffers.order.core.api.command.response.MyPageOrderResponse;
import com.ohgiraffers.order.core.api.command.response.OrderDetailResponse;
import com.ohgiraffers.order.core.domain.Order;
import com.ohgiraffers.order.core.domain.OrderDetail;
import com.ohgiraffers.order.storage.OrderDetailRepository;
import com.ohgiraffers.order.storage.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderReader {

    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;

    public OrderDetailResponse getMeOrders(Long userId) {
        List<Order> orders = orderRepository.findByUserId(userId);

        List<MyPageOrderResponse> myPageOrders = new ArrayList<>();
        for (Order order : orders) {
            OrderDetail detail = orderDetailRepository.findByOrderId(order.getId())
                    .orElseThrow(() -> new CoreException(ErrorType.DEFAULT_ERROR));
            MyPageOrderResponse response = new MyPageOrderResponse(
                    order.getId(),
                    detail.getImageUrl(),
                    detail.getPromotionName(),
                    detail.getQuantity(),
                    detail.getSubtotal(),
                    order.getCreatedAt()
            );
            myPageOrders.add(response);
        }
        return new OrderDetailResponse(myPageOrders);
    }

}
