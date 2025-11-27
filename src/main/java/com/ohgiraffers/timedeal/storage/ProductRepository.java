package com.ohgiraffers.timedeal.storage;

import com.ohgiraffers.timedeal.core.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
