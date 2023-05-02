package com.example.demo.service;

import com.example.demo.dto.request.product.PostProductDto;
import com.example.demo.dto.response.ResponseDto;
import com.example.demo.dto.response.product.DeleteProductResponseDto;
import com.example.demo.dto.response.product.PostProductResponseDto;

public interface ProductService {
    public ResponseDto<PostProductResponseDto> postProduct(String email, PostProductDto dto);
    public ResponseDto<DeleteProductResponseDto> deleteProduct(String email, int boardNumber, int productNumber);
    // public ResponseDto<DeleteProductResponseDto> deleteProduct(String email, DeleteProductDto dto);
}
