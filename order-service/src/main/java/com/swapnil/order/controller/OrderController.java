package com.swapnil.order.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.swapnil.order.OrderDto;
import com.swapnil.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired private OrderService orderService;
    @Autowired private ObjectMapper objectMapper;

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> getOrderById(
            @RequestHeader("userId") Long userId,
            @PathVariable("orderId") Long orderId){
        OrderResponse orderResponse = objectMapper.convertValue(orderService.findById(orderId, userId), OrderResponse.class);
        return new ResponseEntity<>(orderResponse, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAllOrders(
            @RequestHeader("userId") Long userId){
        List<OrderResponse> orders = orderService.findAllOrders(userId).stream()
                                                .map(orderDto -> objectMapper.convertValue(orderDto, OrderResponse.class))
                                                .toList();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@RequestHeader("userId") Long userId,
                                                    @RequestBody OrderRequest request){
        OrderDto orderDto = orderService.createOrder(objectMapper.convertValue(request, OrderDto.class), userId);
        OrderResponse response = objectMapper.convertValue(orderDto, OrderResponse.class);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
}
