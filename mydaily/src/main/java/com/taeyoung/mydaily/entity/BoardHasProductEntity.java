package com.taeyoung.mydaily.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "BoardHasProduct")
@Table(name = "BoardHasProduct")
public class BoardHasProductEntity {
    @Id
    private int boardNumber;
    @Id
    private int productNumber;
}
