package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.LikyEntity;
import com.example.demo.entity.primaryKey.LikyPk;

@Repository
public interface LikyRepository extends JpaRepository<LikyEntity, LikyPk> {
    public List<LikyEntity> findByBoardNumber(int boardNumber);
    public List<LikyEntity> findByUserEmail(String userEmail);
}
