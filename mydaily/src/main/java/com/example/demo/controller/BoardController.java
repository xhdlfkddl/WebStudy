package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.constant.ApiPattern;
import com.example.demo.dto.request.board.LikeDto;
import com.example.demo.dto.request.board.PatchBoardDto;
import com.example.demo.dto.request.board.PostBoardDto;
import com.example.demo.dto.request.board.PostCommentDto;
import com.example.demo.dto.response.ResponseDto;
import com.example.demo.dto.response.board.MyLikeListResponseDto;
import com.example.demo.dto.response.board.PatchBoardResponseDto;
import com.example.demo.dto.response.board.PostBoardResponseDto;
import com.example.demo.dto.response.board.PostCommentResponseDto;
import com.example.demo.dto.response.board.PostMyListResponseDto;
import com.example.demo.dto.response.board.DeleteBoardResponseDto;
import com.example.demo.dto.response.board.GetBoardResponseDto;
import com.example.demo.dto.response.board.GetListResponseDto;
import com.example.demo.dto.response.board.GetSearchTagResponseDto;
import com.example.demo.dto.response.board.GetTop3ListResponseDto;
import com.example.demo.dto.response.board.LikeResponseDto;
import com.example.demo.service.BoardService;

@RestController
@RequestMapping(ApiPattern.BOARD)
public class BoardController {

    private final String POST_BOARD = "";
    private final String PATCH_BOARD = "";
    private final String GET_LIKE_LIST = "/like-list";

    private final String GET_BOARD = "/{boardNumber}";
    private final String GET_MY_LIST ="/my-list";
    private final String GET_SEARCH_TAG = "/search-tag/{tag}";
    private final String GET_LIST = "/list";
    private final String GET_TOP3_LIST = "/top3-list";

    private final String DELETE_BOARD = "/{boardNumber}";

    private final String POST_COMMENT = "/comment";
    private final String LIKE = "/like";

    @Autowired
    private BoardService boardService;

    @PostMapping(POST_BOARD)
    public ResponseDto<PostBoardResponseDto> postBoard(@AuthenticationPrincipal String email, @RequestBody PostBoardDto requestBody) {
        ResponseDto<PostBoardResponseDto> response = boardService.postBoard(email, requestBody);
        return response;
    }

    @PatchMapping(PATCH_BOARD)
    public ResponseDto<PatchBoardResponseDto> patchBoard(@AuthenticationPrincipal String email, @RequestBody PatchBoardDto requestBody) {
        ResponseDto<PatchBoardResponseDto> response = boardService.patchBoard(email, requestBody);
        return response;
    }
    
    @GetMapping(GET_LIKE_LIST)
    public ResponseDto<List<MyLikeListResponseDto>> myLikeList(@AuthenticationPrincipal String email) {
        ResponseDto<List<MyLikeListResponseDto>> response = boardService.myLikeList(email);
        return response;
    }

    @GetMapping(GET_SEARCH_TAG)
    public ResponseDto<List<GetSearchTagResponseDto>> searchTag(@PathVariable("tag")String tag) {
        ResponseDto<List<GetSearchTagResponseDto>> response = boardService.searchTag(tag);
        return response;
    }

    @DeleteMapping(DELETE_BOARD)
    public ResponseDto<DeleteBoardResponseDto> deleteBoard(
        @AuthenticationPrincipal String email,
        @PathVariable("boardNumber") int boardNumber
    ) {
        ResponseDto<DeleteBoardResponseDto> response = boardService.deleteBoard(email, boardNumber);
        return response;
    }

    //?
    @GetMapping(GET_MY_LIST)
    public ResponseDto<List<PostMyListResponseDto>> getMyList(
        @AuthenticationPrincipal String email
    ) {
        ResponseDto<List<PostMyListResponseDto>> response = boardService.getMyList(email);
        return response;
    }

    @GetMapping(GET_BOARD)
    public ResponseDto<GetBoardResponseDto> getBoard(
        @PathVariable("boardNumber") int boardNumber
    ) {
        ResponseDto<GetBoardResponseDto> response = boardService.getBoard(boardNumber);
        return response;
    }

    @PostMapping(POST_COMMENT)
    public ResponseDto<PostCommentResponseDto> postComment(  
        @AuthenticationPrincipal String email,
        @Valid @RequestBody PostCommentDto requestBody
    ) {
        ResponseDto<PostCommentResponseDto> response = boardService.postComment(email, requestBody);
        return response;
    }

    @PostMapping(LIKE)
    public ResponseDto<LikeResponseDto> like(
        @AuthenticationPrincipal String email,
        @Valid @RequestBody LikeDto requestBody
    ) {
        ResponseDto<LikeResponseDto> response = boardService.like(email, requestBody);
        return response;
    }

    @GetMapping(GET_LIST)
    public ResponseDto<List<GetListResponseDto>> getList() {
        ResponseDto<List<GetListResponseDto>> response = boardService.getList();
        return response;
    }

    @GetMapping(GET_TOP3_LIST)
    public ResponseDto<List<GetTop3ListResponseDto>> getTop3List() {
        ResponseDto<List<GetTop3ListResponseDto>> response = boardService.getTop3List();
        return response;
    }
}
