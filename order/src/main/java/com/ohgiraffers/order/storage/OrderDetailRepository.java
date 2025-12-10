package com.ohgiraffers.order.storage;

import com.ohgiraffers.order.core.domain.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

    Optional<OrderDetail> findByOrderId(Long id);
}
