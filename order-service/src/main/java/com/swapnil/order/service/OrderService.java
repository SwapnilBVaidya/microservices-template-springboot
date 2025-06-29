package com.swapnil.order.service;

import com.swapnil.order.OrderDto;
import com.swapnil.order.OrderItemDto;
import com.swapnil.order.dao.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class OrderService {
    @Autowired private OrderDao orderDao;
    @Autowired private RestTemplate restTemplate;
    @Value("${user.service.url}")
    private String userServiceBaseUrl;
    @Value("${product.service.url}")
    private String productServiceBaseUrl;

    public OrderDto findById(Long orderId, Long userId){
        if (HttpStatus.OK !=
                restTemplate.getForEntity(userServiceBaseUrl+"/api/users/"+userId, String.class).getStatusCode())
            throw new RuntimeException("User not found.");
        return orderDao.findById(orderId, userId)
                .orElseThrow(() -> new RuntimeException("Order not found."));
    }

    public List<OrderDto> findAllOrders(Long userId){
        if (HttpStatus.OK !=
                restTemplate.getForEntity(userServiceBaseUrl+"/api/users/"+userId, String.class).getStatusCode())
            throw new RuntimeException("User not found.");
        return orderDao.findAllByUserId(userId);
    }


    public OrderDto createOrder(OrderDto orderDto, Long userId) {
        if (HttpStatus.OK !=
                restTemplate.getForEntity(userServiceBaseUrl+"/api/users/"+userId, String.class).getStatusCode())
            throw new RuntimeException("User not found.");

        ResponseEntity<List<OrderItemDto>> orderItemsResponse =
                restTemplate.exchange(productServiceBaseUrl + "/api/products/check",
                        HttpMethod.POST,
                        new HttpEntity<>(orderDto.getItems()),
                        new ParameterizedTypeReference<>() {});
        if (HttpStatus.OK != orderItemsResponse.getStatusCode())
            throw new RuntimeException("Invalid items or items are out of stock.");

        orderDto.setItems(orderItemsResponse.getBody());
        orderDto.setStatus("PLACED");
        orderDto.setUserId(userId);
        return orderDao.createOrder(orderDto);
    }
}
