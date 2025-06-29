package com.swapnil.product.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.swapnil.product.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductDao {
    @Autowired private ObjectMapper objectMapper;
    @Autowired private ProductRepository productRepository;

    public Optional<ProductDto> findProductById(Long productId){
        return productRepository
                .findById(productId)
                .map(productEntity -> objectMapper.convertValue(productEntity, ProductDto.class));
    }

    public List<ProductDto> findAllProducts() {
        return productRepository
                .findAll().stream()
                .map(productEntity -> objectMapper.convertValue(productEntity, ProductDto.class))
                .toList();
    }

    public List<ProductDto> findAllProductsByIds(List<Long> productIds) {
        return productRepository.findAllById(productIds).stream()
                .map(productEntity -> objectMapper.convertValue(productEntity, ProductDto.class))
                .toList();
    }
}
