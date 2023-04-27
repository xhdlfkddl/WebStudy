package com.example.demo.dto.request.board;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostBoardDto {
    private String boardContent;
    private String boardImageUrl1;
    private String boardImageUrl2;
    private String boardImageUrl3;
    private String tag;
}
