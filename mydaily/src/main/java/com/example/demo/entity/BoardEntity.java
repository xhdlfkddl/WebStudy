package com.example.demo.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.demo.dto.request.board.PatchBoardDto;
import com.example.demo.dto.request.board.PostBoardDto;

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

    public BoardEntity(UserEntity entity, PostBoardDto dto) {

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String now = simpleDateFormat.format(date);

        this.boardContent = dto.getBoardContent();
        this.boardImgUrl1 = dto.getBoardImgUrl1();
        this.boardImgUrl2 = dto.getBoardImgUrl2();
        this.boardImgUrl3 = dto.getBoardImgUrl3();
        this.tag = dto.getTag();
        this.boardWriteTime = now;
        this.writerEmail = entity.getEmail();
        this.writerNickname = entity.getNickname();
        this.writerProfileUrl = entity.getProfile();
        this.commentCount = 0;
        this.likeCount = 0;
        this.viewCount = 0;
    }
    
    public void patch(PatchBoardDto dto) {
        this.boardContent = dto.getBoardContent();
        this.boardImgUrl1 = dto.getBoardImgUrl1();
        this.boardImgUrl2 = dto.getBoardImgUrl2();
        this.boardImgUrl3 = dto.getBoardImgUrl3();
        this.tag = dto.getTag();
    }

    public void patchProfile(String email) {
        this.writerProfileUrl = email;
    }

    public void increaseLikeCount() {
        this.likeCount++;
    }

    public void decreaseLikeCount() {
        this.likeCount--;
    }

    public void increaseCommentCount() {
        this.commentCount++;
    }

}
