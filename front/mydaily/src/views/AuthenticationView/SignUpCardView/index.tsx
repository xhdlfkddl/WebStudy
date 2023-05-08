import { useState } from "react";


import { Box, FormControl, TextField, Button, Input, InputLabel, InputAdornment, IconButton, Typography, Autocomplete } from '@mui/material';
import axios, { AxiosResponse } from 'axios';
import { SignUpDto } from 'src/apis/request/auth';
import ResponseDto from 'src/apis/response';
import { SignUpResponseDto } from 'src/apis/response/auth';
import { SIGN_UP_URL } from 'src/constants/api';
import { useSignUpStore } from 'src/stores';

import VisibilityIcon from '@mui/icons-material/Visibility';
import VisibilityOffIcon from '@mui/icons-material/VisibilityOff';
import { myGender, myHeight, myWeight } from "src/apis/mock";

export default function SignUpCardView() {
    const { email, password, nickname, height, weight, gender } = useSignUpStore();
    const { setEmail, setPassword, setNickname, setHeight, setWeight, setGender } = useSignUpStore();
    const { emailValidate, setEmailValidate, nicknameValidate, setNicknameValidate } = useSignUpStore();
    
    const [ showPassword, setShowPassword ] = useState<boolean>(false);

    //#
    const heightData = {
        options: myHeight.map((option) => option.myHeight)
    };
    const weightData = {
        options: myWeight.map((option) => option.myWeight)
    };
    const genderData = {
        options: myGender.map((option) => option.myGender)
    };
    

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
        <Box sx={{display: 'flex', justifyContent: 'center'}}>
            <Box width='500px' sx={{display: 'flex', flexDirection: 'column', mt:'50px'}}>
                <Typography sx={{fontSize: '25px', mb:'15px'}}>회원가입</Typography>
                <FormControl fullWidth variant="standard">
                    <InputLabel>이메일*</InputLabel>
                    <Input type='text' sx={{mb: '5px'}} endAdornment={
                        <InputAdornment position='end'>
                            <Button size='small' sx={{backgroundColor: 'grey', color: 'white', pl: '10px', pr: '10px', mb: '5px', borderRadius:'10px'}}>중복확인</Button>
                        </InputAdornment>
                    }
                    />
                </FormControl>
                <FormControl fullWidth variant="standard">
                    <InputLabel>비밀번호*</InputLabel>
                    <Input type={showPassword ? 'text' : 'password'} sx={{mb: '5px'}} endAdornment={
                        <InputAdornment position='end'>
                            <IconButton onClick={() => setShowPassword(!showPassword)}>
                                {showPassword ? <VisibilityOffIcon /> : <VisibilityIcon/>}
                            </IconButton>
                        </InputAdornment>
                    }
                    />
                </FormControl>
                <FormControl fullWidth variant="standard">
                    <InputLabel>비밀번호 확인*</InputLabel>
                    <Input type={showPassword ? 'text' : 'password'} sx={{mb: '5px'}} endAdornment={
                        <InputAdornment position='end'>
                            <IconButton onClick={() => setShowPassword(!showPassword)}>
                                {showPassword ? <VisibilityOffIcon /> : <VisibilityIcon/>}
                            </IconButton>
                        </InputAdornment>
                    }
                    />
                </FormControl>
                <FormControl fullWidth variant="standard">
                    <InputLabel>닉네임*</InputLabel>
                    <Input type='text' sx={{mb: '5px'}} endAdornment={
                        <InputAdornment position='end'>
                            <Button size='small' sx={{backgroundColor: 'grey', color: 'white', pl: '10px', pr: '10px', mb: '5px', borderRadius:'10px'}}>중복확인</Button>
                        </InputAdornment>
                    }
                    />
                </FormControl>
                <FormControl fullWidth variant="standard">
                    <Autocomplete
                        {...heightData}
                        id="disable-close-on-select"
                        disableCloseOnSelect
                        renderInput={(params) => (
                        <TextField {...params} label="키" variant="standard" />
                        )}
                    />
                </FormControl>
                <FormControl fullWidth variant="standard">
                    <Autocomplete
                            {...weightData}
                            id="disable-close-on-select"
                            disableCloseOnSelect
                            renderInput={(params) => (
                            <TextField {...params} label="체중" variant="standard" />
                            )}
                        />
                </FormControl>
                <FormControl fullWidth variant="standard">
                    <Autocomplete
                            {...genderData}
                            id="disable-close-on-select"
                            disableCloseOnSelect
                            renderInput={(params) => (
                            <TextField {...params} label="성별" variant="standard" />
                            )}
                        />
                </FormControl>
                <Button fullWidth sx={{mt: '70px',backgroundColor: 'black', color: 'white'}} onClick={onSignUpHandler}>회원가입</Button>
            </Box>
        </Box>
    )
}