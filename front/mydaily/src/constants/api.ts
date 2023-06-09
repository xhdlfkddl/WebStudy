export const authorizationHeader = (accessToken: string) => {
    return {headers: {Authorization: `Bearer ${accessToken}`}}
}

export const multipartHeader = () => {
    return { headers: {'Content-Type': 'multipart/form-data'} };
}

export const HOST = "http://localhost:4040/";

export const SIGN_UP_URL = `${HOST}auth/sign-up`;
export const SIGN_IN_URL = `${HOST}auth/sign-in`;

export const GET_USER = `${HOST}api/user/`;
export const GET_MY_LIST = `${HOST}api/board/my-list`;
export const GET_LIST = `${HOST}api/board/list`;
export const GET_TOP_3_LIST = `${HOST}api/board/top3-list`;
export const GET_TOP_15_SEARCH_TAG = `${HOST}api/board/top15-search-word`;
export const GET_MY_LIKE_LIST = `${HOST}api/board/like-list`;
export const VALIDATE_EMAIL_URL = `${HOST}api/user/validate/email`;
export const VALIDATE_NICKNAME_URL = `${HOST}api/user/validate/nickname`;
export const PATCH_PROFILE_URL = `${HOST}api/user/patch-profile`;

export const POST_COMMENT_URL = `${HOST}api/board/comment`;
export const LIKE_URL = `${HOST}api/board/like`;

export const GET_BOARD_URL = (boardNumber: string) => `${HOST}api/board/${boardNumber}`;
export const SEARCH_TAG_URL = (tag: string) => `${HOST}api/board/search-tag/${tag}`;
export const DELETE_BOARD_URL = (boardNumber: string) => `${HOST}api/board/${boardNumber}`;

export const FILE_UPLOAD_URL = `${HOST}file/upload`;