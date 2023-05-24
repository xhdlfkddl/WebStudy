interface GetTop3ListResponseDto {
    boardNumber: number;
    boardContent: string;
    boardImgUrl: string;
    boardWriteDatetime: string;
    viewCount: number;
    writerNickname: string;
    commentCount: number;
    likeCount: number;
}

export default GetTop3ListResponseDto;