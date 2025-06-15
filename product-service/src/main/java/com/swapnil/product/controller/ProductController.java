package com.swapnil.product.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable("productId") String productId){
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(productId);
        productResponse.setName("John Cena");
        productResponse.setDescription("Product details");
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }
}
