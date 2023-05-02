package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.constant.ApiPattern;
import com.example.demo.dto.request.product.PostProductDto;
import com.example.demo.dto.response.ResponseDto;
import com.example.demo.dto.response.product.DeleteProductResponseDto;
import com.example.demo.dto.response.product.PostProductResponseDto;
import com.example.demo.service.ProductService;

@RestController
@RequestMapping(ApiPattern.PRODUCT)
public class ProductController {

    private final String POST_PRODUCT = "/post-product";
    private final String DELETE_PRODUCT = "/{boardNumber}/{productNumber}";

    @Autowired
    private ProductService productService;

    @PostMapping(POST_PRODUCT)
    public ResponseDto<PostProductResponseDto> postProduct(@AuthenticationPrincipal String email, @RequestBody PostProductDto requestBody) {
        ResponseDto<PostProductResponseDto> response = productService.postProduct(email, requestBody);
        return response;
    }

    @DeleteMapping(DELETE_PRODUCT)
    public ResponseDto<DeleteProductResponseDto> deleteProduct(@AuthenticationPrincipal String email, @PathVariable("boardNumber") int boardNumber, @PathVariable("productNumber") int productNumber) {
    // public ResponseDto<DeleteProductResponseDto> deleteProduct(@AuthenticationPrincipal String email, DeleteProductDto requestBody) {
        ResponseDto<DeleteProductResponseDto> response = productService.deleteProduct(email, boardNumber, productNumber);
        return response;
    }
    
}
