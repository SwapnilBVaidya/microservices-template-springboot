package com.swapnil.product.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.swapnil.product.OrderItemDto;
import com.swapnil.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired private ObjectMapper objectMapper;
    @Autowired private ProductService productService;

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponse> getProductById(
            @RequestHeader("userId") Long userId,
            @PathVariable("productId") Long productId){
        ProductResponse productResponse = objectMapper.convertValue(productService.findById(productId, userId), ProductResponse.class);
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts(
            @RequestHeader("userId") Long userId){
        List<ProductResponse> products = productService
                                            .findAllProducts(userId).stream()
                                            .map(productDto -> objectMapper.convertValue(productDto, ProductResponse.class))
                                            .toList();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping("/check")
    public ResponseEntity<List<OrderItemDto>> checkProducts(@RequestBody List<OrderItemDto> items){
        return new ResponseEntity<>(productService.checkOrderItems(items), HttpStatus.OK);
    }
}
