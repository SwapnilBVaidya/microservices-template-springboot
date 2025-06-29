package com.swapnil.order.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    Optional<OrderEntity> findByIdAndUserId(Long orderId, Long userId);
    List<OrderEntity> findAllByUserId(Long userId);
}
