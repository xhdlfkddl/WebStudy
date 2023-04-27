package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.dto.request.board.PostBoardDto;
import com.example.demo.dto.response.ResponseDto;
import com.example.demo.dto.response.board.PostBoardResponseDto;

@Service
public interface BoardService {
    public ResponseDto<PostBoardResponseDto> postBoard(String email, PostBoardDto postBoardDto);
}
