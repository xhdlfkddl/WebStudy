package com.taeyoung.mydaily.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.taeyoung.mydaily.entity.primaryKey.BoardHasProductPk;

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
    private BoardHasProductPk boardHasProductPk;
}
