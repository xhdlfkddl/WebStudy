package com.taeyoung.mydaily.controller;

import com.taeyoung.mydaily.common.constant.ApiPattern;
import com.taeyoung.mydaily.dto.request.auth.SignUpDto;
import com.taeyoung.mydaily.dto.response.ResponseDto;
import com.taeyoung.mydaily.dto.response.auth.SignUpResponseDto;
import com.taeyoung.mydaily.service.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiPattern.AUTH) // "/auth"
public class AuthController {
    
    private final String SIGN_UP = "/sign-up";

    @Autowired private AuthService authService;

    @PostMapping(SIGN_UP)
    public ResponseDto<SignUpResponseDto> signUp(SignUpDto requestBody) {
        ResponseDto<SignUpResponseDto> response = authService.signUp(requestBody);
        return response;
    }

}
