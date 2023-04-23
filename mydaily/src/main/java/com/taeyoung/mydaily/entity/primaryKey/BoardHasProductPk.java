package com.taeyoung.mydaily.entity.primaryKey;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class BoardHasProductPk implements Serializable {
    @Column(name = "board_number")
    private int boardNumber;
    @Column(name = "product_number")
    private int productNumber;
}
