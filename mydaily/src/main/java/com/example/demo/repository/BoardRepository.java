package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.BoardEntity;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Integer> {
    public BoardEntity findByBoardNumber(int boardNumber);
    public List<BoardEntity> findByWriterEmail(String writerEmail);
    public List<BoardEntity> findByWriterEmailOrderByBoardWriteTimeDesc(String email);
    public List<BoardEntity> findByTag(String tag);
    public List<BoardEntity> findByOrderByBoardWriteTimeDesc();
    public List<BoardEntity> findTop3ByBoardWriteTimeGreaterThanOrderByLikeCountDesc(String date);
}
