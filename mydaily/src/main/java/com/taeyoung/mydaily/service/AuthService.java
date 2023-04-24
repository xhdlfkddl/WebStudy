package com.taeyoung.mydaily.service;

import com.taeyoung.mydaily.dto.request.auth.SignInDto;
import com.taeyoung.mydaily.dto.request.auth.SignUpDto;
import com.taeyoung.mydaily.dto.response.ResponseDto;
import com.taeyoung.mydaily.dto.response.auth.SignInResponseDto;
import com.taeyoung.mydaily.dto.response.auth.SignUpResponseDto;

public interface AuthService {
    public ResponseDto<SignUpResponseDto> signUp(SignUpDto signUpDto);
    public ResponseDto<SignInResponseDto> signIn(SignInDto signInDto);
}
