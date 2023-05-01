package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.constant.ApiPattern;
import com.example.demo.dto.request.board.PatchBoardDto;
import com.example.demo.dto.request.board.PostBoardDto;
import com.example.demo.dto.response.ResponseDto;
import com.example.demo.dto.response.board.MyLikeListResponseDto;
import com.example.demo.dto.response.board.PatchBoardResponseDto;
import com.example.demo.dto.response.board.PostBoardResponseDto;
import com.example.demo.dto.response.board.GetSearchTagResponseDto;
import com.example.demo.service.BoardService;

@RestController
@RequestMapping(ApiPattern.BOARD)
public class BoardController {

    private final String POST_BOARD = "";
    private final String PATCH_BOARD = "";
    private final String POST_LIKE_LIST = "/like-list";
    private final String GET_SEARCH_TAG = "/search-tag/{tag}";

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
    
    @PostMapping(POST_LIKE_LIST)
    public ResponseDto<List<MyLikeListResponseDto>> myLikeList(@AuthenticationPrincipal String email) {
        ResponseDto<List<MyLikeListResponseDto>> response = boardService.myLikeList(email);
        return response;
    }

    @GetMapping(GET_SEARCH_TAG)
    public ResponseDto<List<GetSearchTagResponseDto>> searchTag(@PathVariable("tag")String tag) {
        ResponseDto<List<GetSearchTagResponseDto>> response = boardService.searchTag(tag);
        return response;
    }
}
