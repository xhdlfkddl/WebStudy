package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.ProductEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
    public ProductEntity findById(int productNumber);
    public ProductEntity findByProductNumber(int productNumber);
    @Transactional
    public void deleteByProductNumber(int productNumber);
}
