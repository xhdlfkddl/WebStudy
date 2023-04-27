package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.example.demo.entity.primaryKey.BoardHasProductPk;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "BoardHasProduct")
@Table(name = "BoardHasProduct")
@IdClass(BoardHasProductPk.class)
public class BoardHasProductEntity {
    @Id
    private int boardNumber;
    @Id
    private int productNumber;
}
