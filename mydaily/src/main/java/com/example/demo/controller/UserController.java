package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.constant.ApiPattern;
import com.example.demo.dto.request.user.PatchProfileDto;
import com.example.demo.dto.response.ResponseDto;
import com.example.demo.dto.response.user.PatchProfileResponseDto;
import com.example.demo.service.UserService;

@RestController
@RequestMapping(ApiPattern.USER)
public class UserController {
    
    private final String PATCH_PROFILE = "patch-profile";

    @Autowired
    private UserService userService;

    @PatchMapping(PATCH_PROFILE)
    public ResponseDto<PatchProfileResponseDto> patchProfile(@AuthenticationPrincipal String email,@RequestBody PatchProfileDto requestBody) {
        ResponseDto<PatchProfileResponseDto> response = userService.patchProfile(email, requestBody);
        return response;
    }

}
