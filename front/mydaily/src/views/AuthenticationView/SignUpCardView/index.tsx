import { Box, FormControl, TextField, Button } from '@mui/material';
import axios, { AxiosResponse } from 'axios';
import { SignUpDto } from 'src/apis/request/auth';
import ResponseDto from 'src/apis/response';
import { SignUpResponseDto } from 'src/apis/response/auth';
import { SIGN_UP_URL } from 'src/constants/api';
import { useSignUpStore } from 'src/stores';

export default function SignUpCardView() {
    const { email, password, nickname, height, weight, gender } = useSignUpStore();
    const { setEmail, setPassword, setNickname, setHeight, setWeight, setGender } = useSignUpStore();

    const onSignUpHandler = () => {
        const data: SignUpDto = { email, password, nickname, height, weight, gender };

        axios.post(SIGN_UP_URL, data)
        .then((response) => signUpResponseHandler(response))
        .catch((error) => signUpErrorHandler(error));
    }

    const signUpResponseHandler = (response: AxiosResponse<any, any>) => {
        const { result, message } = response.data as ResponseDto<SignUpResponseDto>; 
        if (result) alert(message);
    }

    const signUpErrorHandler = (error: any) => {
        console.log(error.response.status);
    }

    return(
        <Box width='400px' height='400px' sx={{mt: '20px', ml: '20px'}}>
            <FormControl fullWidth variant="standard">
                <TextField sx={{mb: '15px'}} type="text" label="이메일" value={email} onChange={(event) => setEmail(event.target.value)}/>
                <TextField sx={{mb: '15px'}} type="text" label="비밀번호" value={password} onChange={(event) => setPassword(event.target.value)}/>
                <TextField sx={{mb: '15px'}} type="text" label="닉네임" value={nickname} onChange={(event) => setNickname(event.target.value)}/>
                <TextField sx={{mb: '15px'}} type="text" label="키" value={height} onChange={(event) => setHeight(event.target.value)}/>
                <TextField sx={{mb: '15px'}} type="text" label="몸무게" value={weight} onChange={(event) => setWeight(event.target.value)}/>
                <TextField sx={{mb: '15px'}} type="text" label="성별" value={gender} onChange={(event) => setGender(event.target.value)}/>
            </FormControl>
            <Button fullWidth sx={{backgroundColor: 'skyblue', color: 'black'}} onClick={onSignUpHandler}>회원가입</Button>
        </Box>
    )
}