package com.ohgiraffers.timedeal.storage;

import com.ohgiraffers.timedeal.core.domain.Example;
import com.ohgiraffers.timedeal.core.enums.EntityStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExampleRepository extends JpaRepository<Example, Long>, ExampleRepositoryCustom {
    boolean existsByExampleAndStatus(String example, EntityStatus status);
}
