package com.example.demo.dto.response.product;

import com.example.demo.entity.ProductEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostProductResponseDto {
    private int productNumber;
    private String productName;
    private int productPrice;
    private String productUrl;
    private String productImgUrl;

    public PostProductResponseDto(ProductEntity entity) {
        this.productNumber = entity.getProductNumber();
        this.productName = entity.getProductName();
        this.productPrice = entity.getProductPrice();
        this.productUrl = entity.getProductUrl();
        this.productImgUrl = entity.getProductImgUrl();
    }
}
