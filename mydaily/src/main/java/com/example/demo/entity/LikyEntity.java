package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.example.demo.entity.primaryKey.LikyPk;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Liky")
@Table(name = "Liky")
@IdClass(LikyPk.class)
public class LikyEntity {
    @Id
    private int boardNumber;
    @Id
    private String userEmail;
    private String userNickname;
    private String userProfileUrl;

    public void patchProfile(String profileUrl) {
        this.userProfileUrl = profileUrl;
    }

    public LikyEntity(UserEntity userEntity, int boardNumber) {
        this.userEmail = userEntity.getEmail();
        this.boardNumber = boardNumber;
        this.userNickname = userEntity.getNickname();
        this.userProfileUrl = userEntity.getProfile();
    }
}
