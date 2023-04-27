package com.example.demo.entity.primaryKey;

import java.io.Serializable;

import javax.persistence.Column;
import lombok.Data;


@Data
public class LikyPk implements Serializable {
    // 컬럼명과 동일하게 적어야함
    @Column(name = "board_number")
    private int boardNumber;
    @Column(name = "user_email")
    private String userEmail;
}

