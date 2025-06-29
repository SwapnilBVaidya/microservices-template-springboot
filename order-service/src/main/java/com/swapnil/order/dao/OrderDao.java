package com.swapnil.order.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.swapnil.order.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class OrderDao {
    @Autowired private ObjectMapper objectMapper;
    @Autowired private OrderRepository orderRepository;

    public Optional<OrderDto> findById(Long orderId, Long userId){
        return orderRepository
                .findByIdAndUserId(orderId, userId)
                .map(orderEntity -> objectMapper.convertValue(orderEntity, OrderDto.class));
    }

    public List<OrderDto> findAllByUserId(Long userId){
        return orderRepository
                .findAllByUserId(userId).stream()
                .map(orderEntity -> objectMapper.convertValue(orderEntity, OrderDto.class))
                .toList();
    }

    public OrderDto createOrder(OrderDto orderDto) {
        OrderEntity entity = orderRepository.save(objectMapper.convertValue(orderDto, OrderEntity.class));
        return objectMapper.convertValue(entity, OrderDto.class);
    }
}
