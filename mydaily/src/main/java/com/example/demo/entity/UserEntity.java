package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.demo.dto.request.auth.SignUpDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
// 해당 클래스가 Entity 클래스이며
// Entity 이름을 "User"라고 설정
@Entity(name = "User")
// 해당 클래스를 데이터베이스의 "User"이름을 가진 테이블과 매핑시킴
// 데이터베이스와 일치하는 이름으로 설정
@Table(name = "User")
public class UserEntity {
    @Id
    private String email;
    private String password;
    private String nickname;
    private String profile;
    private String height;
    private String weight;
    private String gender;

    public UserEntity(SignUpDto signUpDto) {
        this.email = signUpDto.getEmail();
        this.password = signUpDto.getPassword();
        this.nickname = signUpDto.getNickname();
        this.height = signUpDto.getHeight();
        this.weight = signUpDto.getWeight();
        this.gender = signUpDto.getGender();
    }

    public void patchProfile(String profileUrl) {
        this.profile = profileUrl;
    }

}
