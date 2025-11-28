package com.ohgiraffers.timedeal.storage;

import com.ohgiraffers.timedeal.core.domain.Order;
import com.ohgiraffers.timedeal.core.domain.product;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

public interface OrderRepository extends JpaRepository<Order,Integer> {
}
