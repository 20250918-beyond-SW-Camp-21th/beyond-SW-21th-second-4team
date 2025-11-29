package com.ohgiraffers.timedeal.storage;

import com.ohgiraffers.timedeal.core.domain.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderDetailRepository extends JpaRepository<OrderDetail,Integer> {

    Optional<OrderDetail> findByOrderId(Long id);
}
