package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.request.board.LikeDto;
import com.example.demo.dto.request.board.PatchBoardDto;
import com.example.demo.dto.request.board.PostBoardDto;
import com.example.demo.dto.request.board.PostCommentDto;
import com.example.demo.dto.response.ResponseDto;
import com.example.demo.dto.response.board.DeleteBoardResponseDto;
import com.example.demo.dto.response.board.GetBoardResponseDto;
import com.example.demo.dto.response.board.GetListResponseDto;
import com.example.demo.dto.response.board.GetSearchTagResponseDto;
import com.example.demo.dto.response.board.GetTop3ListResponseDto;
import com.example.demo.dto.response.board.LikeResponseDto;
import com.example.demo.dto.response.board.MyLikeListResponseDto;
import com.example.demo.dto.response.board.PatchBoardResponseDto;
import com.example.demo.dto.response.board.PostBoardResponseDto;
import com.example.demo.dto.response.board.PostCommentResponseDto;
import com.example.demo.dto.response.board.PostMyListResponseDto;

@Service
public interface BoardService {
    public ResponseDto<PostBoardResponseDto> postBoard(String email, PostBoardDto postBoardDto);
    public ResponseDto<PatchBoardResponseDto> patchBoard(String email, PatchBoardDto patchBoardDto);
    public ResponseDto<List<MyLikeListResponseDto>> myLikeList(String email);
    public ResponseDto<GetBoardResponseDto> getBoard(int boardNumber);
    public ResponseDto<List<GetSearchTagResponseDto>> searchTag(String tag);
    public ResponseDto<DeleteBoardResponseDto> deleteBoard(String email, int boardNumber);
    public ResponseDto<PostCommentResponseDto> postComment(String email, PostCommentDto dto);
    public ResponseDto<LikeResponseDto> like(String email, LikeDto dto);
    public ResponseDto<List<PostMyListResponseDto>> getMyList(String email);
    public ResponseDto<List<GetTop3ListResponseDto>> getTop3List();
    public ResponseDto<List<GetListResponseDto>> getList();
}
