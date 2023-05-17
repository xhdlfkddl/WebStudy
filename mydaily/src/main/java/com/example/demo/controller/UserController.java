package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.constant.ApiPattern;
import com.example.demo.dto.request.user.PatchProfileDto;
import com.example.demo.dto.request.user.ValidateEmailDto;
import com.example.demo.dto.request.user.ValidateNicknameDto;
import com.example.demo.dto.response.ResponseDto;
import com.example.demo.dto.response.user.GetUserResponseDto;
import com.example.demo.dto.response.user.PatchProfileResponseDto;
import com.example.demo.dto.response.user.ValidateEmailResponseDto;
import com.example.demo.dto.response.user.ValidateNicknameResponseDto;
import com.example.demo.service.UserService;

@RestController
@RequestMapping(ApiPattern.USER)
public class UserController {
    
    private final String GET_USER = "/";
    private final String PATCH_PROFILE = "patch-profile";
    private final String VALIDATE_EMAIL = "/validate/email";
    private final String VALIDATE_NICKNAME = "/validate/nickname";

    @Autowired
    private UserService userService;

    @GetMapping(GET_USER)
    public ResponseDto<GetUserResponseDto> getUser(@AuthenticationPrincipal String email) {
        ResponseDto<GetUserResponseDto> response = userService.getUser(email);
        return response;
    }

    @PostMapping(VALIDATE_EMAIL)
    public ResponseDto<ValidateEmailResponseDto> validateEmail(@RequestBody ValidateEmailDto requestBody) {
        ResponseDto<ValidateEmailResponseDto> response = userService.validateEmail(requestBody);
        return response;
    }

    @PostMapping(VALIDATE_NICKNAME)
    public ResponseDto<ValidateNicknameResponseDto> validateNickname(@RequestBody ValidateNicknameDto requestBody) {
        ResponseDto<ValidateNicknameResponseDto> response = userService.validateNickname(requestBody);
        return response;
    }

    @PatchMapping(PATCH_PROFILE)
    public ResponseDto<PatchProfileResponseDto> patchProfile(@AuthenticationPrincipal String email,@RequestBody PatchProfileDto requestBody) {
        ResponseDto<PatchProfileResponseDto> response = userService.patchProfile(email, requestBody);
        return response;
    }

}
