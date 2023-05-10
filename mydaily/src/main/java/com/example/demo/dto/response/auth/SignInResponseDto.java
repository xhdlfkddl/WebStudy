package com.example.demo.dto.response.auth;

import com.example.demo.entity.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignInResponseDto {
    private String email;
    private String nickname;
    private String profile;
    private String height;
    private String weight;
    private String gender;
    private String token;
    private int expiredTime;

    public SignInResponseDto(UserEntity userEntity, String token) {
        this.email = userEntity.getEmail();
        this.nickname = userEntity.getNickname();
        this.profile = userEntity.getProfile();
        this.height = userEntity.getHeight();
        this.weight = userEntity.getWeight();
        this.gender = userEntity.getGender();
        this.token = token;
        this.expiredTime = 3600000; // 1시간
    }
}
