export const HOST = "http://localhost:4040/";

export const SIGN_UP_URL = `${HOST}auth/sign-up`;
export const SIGN_IN_URL = `${HOST}auth/sign-in`;

export const VALIDATE_EMAIL_URL = `${HOST}api/user/validate/email`;
export const VALIDATE_NICKNAME_URL = `${HOST}api/user/validate/nickname`;

export const GET_BOARD_URL = (boardNumber: string) => `${HOST}api/board/${boardNumber}`;