package com.taeyoung.mydaily.controller;

import com.taeyoung.mydaily.common.constant.ApiPattern;
import com.taeyoung.mydaily.dto.request.auth.SignInDto;
import com.taeyoung.mydaily.dto.request.auth.SignUpDto;
import com.taeyoung.mydaily.dto.response.ResponseDto;
import com.taeyoung.mydaily.dto.response.auth.SignInResponseDto;
import com.taeyoung.mydaily.dto.response.auth.SignUpResponseDto;
import com.taeyoung.mydaily.service.AuthService;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiPattern.AUTH) // "/auth"
public class AuthController {
    
    private final String SIGN_UP = "/sign-up";
    private final String SIGN_IN = "/sign-in";

    @Autowired 
    private AuthService authService;

    @PostMapping(SIGN_UP)
    public ResponseDto<SignUpResponseDto> signUp(@Valid @RequestBody SignUpDto requestBody) {
        ResponseDto<SignUpResponseDto> response = authService.signUp(requestBody);
        return response;
    }

    @PostMapping(SIGN_IN)
    public ResponseDto<SignInResponseDto> signIn(@RequestBody SignInDto requestBody) {
        ResponseDto<SignInResponseDto> response = authService.signIn(requestBody);
        return response;
    }
}
