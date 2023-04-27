package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.demo.dto.request.product.PostProductDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Product")
@Table(name = "Product")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productNumber;
    private String productName;
    private int productPrice;
    private String productUrl;
    private String productImgUrl;

    public ProductEntity(PostProductDto dto) {
        this.productName = dto.getProductName();
        this.productPrice = dto.getProductPrice();
        this.productUrl = dto.getProductUrl();
        this.productImgUrl = dto.getProductImgUrl();
    }
}
