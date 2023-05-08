package com.example.demo.service;

import com.example.demo.dto.request.user.PatchProfileDto;
import com.example.demo.dto.request.user.ValidateEmailDto;
import com.example.demo.dto.request.user.ValidateNicknameDto;
import com.example.demo.dto.response.ResponseDto;
import com.example.demo.dto.response.user.PatchProfileResponseDto;
import com.example.demo.dto.response.user.ValidateEmailResponseDto;
import com.example.demo.dto.response.user.ValidateNicknameResponseDto;

public interface UserService {
    public ResponseDto<ValidateEmailResponseDto > validateEmail(ValidateEmailDto ValidateEmailDto);
    public ResponseDto<ValidateNicknameResponseDto > validateNickname(ValidateNicknameDto ValidateNicknameDto);
    public ResponseDto<PatchProfileResponseDto> patchProfile(String email, PatchProfileDto patchProfileDto);
}
