interface CommentInterface {
    commentNumber: number;
    writerEmail: string;
    boardNumber: number;
    writerDate: string;
    commentContent: string;
    writerProfileUrl?: string | null;
    writerNickname: string;
}

export default CommentInterface;