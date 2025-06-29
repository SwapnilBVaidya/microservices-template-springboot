package com.swapnil.product.service;

import com.swapnil.product.OrderItemDto;
import com.swapnil.product.ProductDto;
import com.swapnil.product.dao.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired private ProductDao productDao;
    @Autowired private RestTemplate restTemplate;
    @Value("${user.service.url}")
    private String userServiceBaseUrl;

    public ProductDto findById(Long productId, Long userId){
        if (HttpStatus.OK !=
                restTemplate.getForEntity(userServiceBaseUrl+"/api/users/"+userId, String.class).getStatusCode())
            throw new RuntimeException("User not found.");
        return productDao.findProductById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found."));
    }

    public List<ProductDto> findAllProducts(Long userId){
        if (HttpStatus.OK !=
                restTemplate.getForEntity(userServiceBaseUrl+"/api/users/"+userId, String.class).getStatusCode())
            throw new RuntimeException("User not found.");
        return productDao.findAllProducts();
    }

    public List<OrderItemDto> checkOrderItems(List<OrderItemDto> items){
        Map<Long, OrderItemDto> orderItemDtoMap = items.stream()
                                            .collect(Collectors.toMap(OrderItemDto::getProductId, Function.identity()));
        List<Long> productIds = items.stream()
                                .map(OrderItemDto::getProductId).toList();
        List<ProductDto> products = productDao.findAllProductsByIds(productIds);

        if (productIds.size() != products.size())
            throw new RuntimeException("Products not found.");
        return products.stream()
                .map(productDto -> {
                    OrderItemDto orderItemDto = new OrderItemDto();
                    orderItemDto.setProductId(productDto.getId());
                    orderItemDto.setPrice(productDto.getPrice());
                    orderItemDto.setQuantity(orderItemDtoMap.get(productDto.getId()).getQuantity());
                    return orderItemDto;
                }).toList();
    }
}
