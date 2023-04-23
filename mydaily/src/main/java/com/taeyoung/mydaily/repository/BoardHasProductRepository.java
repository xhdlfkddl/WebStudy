package com.taeyoung.mydaily.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.taeyoung.mydaily.entity.BoardHasProductEntity;
import com.taeyoung.mydaily.entity.primaryKey.BoardHasProductPk;

@Repository
public interface BoardHasProductRepository extends JpaRepository<BoardHasProductEntity, BoardHasProductPk> {
    
}
