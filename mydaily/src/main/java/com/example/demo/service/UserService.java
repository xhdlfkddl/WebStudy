package com.example.demo.service;

import com.example.demo.dto.request.user.PatchProfileDto;
import com.example.demo.dto.response.ResponseDto;
import com.example.demo.dto.response.user.PatchProfileResponseDto;

public interface UserService {
    public ResponseDto<PatchProfileResponseDto> patchProfile(String email, PatchProfileDto patchProfileDto);
}
