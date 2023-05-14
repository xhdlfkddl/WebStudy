interface BoardInterface {
    boardNumber: number,
    boardContent: string | null,
    boardImgUrl1: string,
    boardImgUrl2?: string | null,
    boardImgUrl3?: string | null,
    tag: string,
    boardWriteTime: string,
    writerEmail: string,
    writerNickname: string,
    writerProfileUrl?: string | null,
    commentCount: number,
    likeCount: number,
    viewCount: number
}

export default BoardInterface;