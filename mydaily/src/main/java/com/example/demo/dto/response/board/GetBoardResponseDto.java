package com.example.demo.dto.response.board;

import java.util.List;

import com.example.demo.entity.BoardEntity;
import com.example.demo.entity.CommentEntity;
import com.example.demo.entity.LikyEntity;
import com.example.demo.entity.ProductEntity;
import com.example.demo.entity.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetBoardResponseDto {
    private BoardEntity board;

    private UserEntity user;

    private List<CommentEntity> commentList;

    private List<LikyEntity> likeList;

    private List<ProductEntity> productList;
}
