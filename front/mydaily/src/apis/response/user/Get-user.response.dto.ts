interface GetUserResponseDto {
    email: string;
    nickname: string;
    profile: string | null;
    height: string;
    weight: string;
    gender: string;
}

export default GetUserResponseDto;