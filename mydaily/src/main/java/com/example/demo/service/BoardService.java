package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.request.board.PatchBoardDto;
import com.example.demo.dto.request.board.PostBoardDto;
import com.example.demo.dto.response.ResponseDto;
import com.example.demo.dto.response.board.DeleteBoardResponseDto;
import com.example.demo.dto.response.board.GetSearchTagResponseDto;
import com.example.demo.dto.response.board.MyLikeListResponseDto;
import com.example.demo.dto.response.board.PatchBoardResponseDto;
import com.example.demo.dto.response.board.PostBoardResponseDto;

@Service
public interface BoardService {
    public ResponseDto<PostBoardResponseDto> postBoard(String email, PostBoardDto postBoardDto);
    public ResponseDto<PatchBoardResponseDto> patchBoard(String email, PatchBoardDto patchBoardDto);
    public ResponseDto<List<MyLikeListResponseDto>> myLikeList(String email);
    public ResponseDto<List<GetSearchTagResponseDto>> searchTag(String tag);
    public ResponseDto<DeleteBoardResponseDto> deleteBoard(String email, int boardNumber);
}
