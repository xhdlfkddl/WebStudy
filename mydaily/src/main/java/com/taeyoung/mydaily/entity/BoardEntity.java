package com.taeyoung.mydaily.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Board")
@Table(name = "Board")
public class BoardEntity {
    @Id
    // 기본키를 자동으로 생성해주는 annotation 
    // IDENTITY = AUTO_INCREMENT
    // SEQUENCE = 오라클, Postgre 시퀀스를 지원
    // TABLE    = 키 생성 전용 테이블을 만들고 값을 만들어 시퀀스를 흉내내는것
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int boardNumber;
    private String boardContent;
    private String boardImgUrl1;
    private String boardImgUrl2;
    private String boardImgUrl3;
    private String tag;
    private String boardWriteTime;
    private String writerEmail;
    private String writerNickname;
    private String writerProfileUrl;
    private int commentCount;
    private int likeCount;
    private int viewCount;

}
