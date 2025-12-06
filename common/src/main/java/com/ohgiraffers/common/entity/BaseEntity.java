package com.ohgiraffers.common.entity;

import com.ohgiraffers.common.enums.EntityStatus;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private EntityStatus status = EntityStatus.ACTIVE;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column
    private LocalDateTime updatedAt;

    public void active() {
        status = EntityStatus.ACTIVE;
    }

    public boolean isActive() {
        return status == EntityStatus.ACTIVE;
    }

    public void deleted() {
        status = EntityStatus.DELETED;
    }
}
