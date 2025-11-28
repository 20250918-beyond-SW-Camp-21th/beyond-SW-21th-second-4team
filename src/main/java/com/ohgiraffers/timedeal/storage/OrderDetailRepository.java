package com.ohgiraffers.timedeal.storage;

import com.ohgiraffers.timedeal.core.domain.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail,Integer> {

    List<OrderDetail> findByOrderId(Long id);
}
