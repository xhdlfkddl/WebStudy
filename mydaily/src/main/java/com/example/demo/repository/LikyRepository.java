package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.LikyEntity;
import com.example.demo.entity.primaryKey.LikyPk;

@Repository
public interface LikyRepository extends JpaRepository<LikyEntity, LikyPk> {
    public List<LikyEntity> findByBoardNumber(int boardNumber);
    public List<LikyEntity> findByUserEmail(String userEmail);
    public LikyEntity findByUserEmailAndBoardNumber(String userEmail, int boardNumber);
    @Transactional
    public void deleteByBoardNumber(int boardNumber);
}
