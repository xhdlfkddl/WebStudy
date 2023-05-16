export const authorizationHeader = (accessToken: string) => {
    return {headers: {Authorization: `Bearer ${accessToken}`}}
}

export const HOST = "http://localhost:4040/";

export const SIGN_UP_URL = `${HOST}auth/sign-up`;
export const SIGN_IN_URL = `${HOST}auth/sign-in`;

export const GET_USER = `${HOST}api/user/`;
export const VALIDATE_EMAIL_URL = `${HOST}api/user/validate/email`;
export const VALIDATE_NICKNAME_URL = `${HOST}api/user/validate/nickname`;

export const POST_COMMENT_URL = `${HOST}api/board/comment`;
export const LIKE_URL = `${HOST}api/board/like`;


export const GET_BOARD_URL = (boardNumber: string) => `${HOST}api/board/${boardNumber}`;