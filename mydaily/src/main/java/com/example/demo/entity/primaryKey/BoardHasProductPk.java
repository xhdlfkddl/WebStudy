package com.example.demo.entity.primaryKey;

import java.io.Serializable;

import javax.persistence.Column;

import lombok.Data;
@Data
public class BoardHasProductPk implements Serializable {
    @Column(name = "board_number")
    private int boardNumber;
    @Column(name = "product_number")
    private int productNumber;
}
