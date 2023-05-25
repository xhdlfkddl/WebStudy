import { Avatar, Box, Typography, IconButton, ImageList, ImageListItem } from "@mui/material";
import MaleIcon from '@mui/icons-material/Male';
import FemaleIcon from '@mui/icons-material/Female';
import GridViewIcon from '@mui/icons-material/GridView';
import FavoriteBorderIcon from '@mui/icons-material/FavoriteBorder';
import GridViewRoundedIcon from '@mui/icons-material/GridViewRounded';
import FavoriteIcon from '@mui/icons-material/Favorite';
import { GetMyLikeListResponseDto, GetMyListResponseDto } from "src/apis/response/board";
import { useState, useEffect, useRef, ChangeEventHandler, ChangeEvent } from "react";
import axios, { AxiosResponse } from "axios";
import { FILE_UPLOAD_URL, GET_MY_LIKE_LIST, GET_MY_LIST, PATCH_PROFILE_URL, SEARCH_TAG_URL, authorizationHeader, multipartHeader } from "src/constants/api";
import ResponseDto from "src/apis/response";
import ImgListItem from "src/components/ImgListItem";
import { useCookies } from "react-cookie";
import { useUserStore } from "src/stores";
import { useNavigate } from "react-router-dom";
import { PatchProfileRequestDto } from "src/apis/request/user";
import { PatchProfileResponseDto } from "src/apis/response/user";

export default function MyPageView() {

    const navigator = useNavigate();

    const imageRef = useRef<HTMLInputElement | null>(null);

    const [cookie] = useCookies();
    const { user, setUser } = useUserStore();
    const accessToken = cookie.accessToken;
    const [ myList, setMyList ] = useState<GetMyListResponseDto[]>([]);
    const [ viewFlag, setViewFlag ] = useState<number>(1);

    let isLoad = false;
    
    const getMyList = () => {
        axios.get(GET_MY_LIST, authorizationHeader(accessToken as string))
        .then((response) => getMyListResponseHandler(response))
        .catch((error) => getMyListErrorHandler(error));
    }

    const getMyLikeList = () => {
        axios.get(GET_MY_LIKE_LIST, authorizationHeader(accessToken as string))
        .then((response) => getMyLikeListResponseHandler(response))
        .catch((error) => getMyLikeListErrorHandler(error));
    }

    const onViewMyList = () => {
        setViewFlag(1);
        getMyList();
        return;
    }
    
    const onViewMyLikeList = () => {
        setViewFlag(2);
        getMyLikeList();
        return;
    }

    const onPatchProfileButtonHandler = () => {
        if(!imageRef.current) return;
        imageRef.current.click();
    }

    const onPatchProfileUploadHandler = (event: ChangeEvent<HTMLInputElement>) => {
        if (!event.target.files) return;
        const data = new FormData();
        data.append('file', event.target.files[0]);

        axios.post(FILE_UPLOAD_URL, data, multipartHeader())
        .then((response) => onPatchProfileUploadResponseHandler(response))
        .catch((error) => onPatchProfileUploadErrorHandler(error));
    }
    
    const getMyListResponseHandler = (response: AxiosResponse<any, any>) => {
        const { result, message, data } = response.data as ResponseDto<GetMyListResponseDto[]>;

        if (!result || !data) {
            alert(message);
            return;
        }

        setMyList(data);

    }

    const getMyLikeListResponseHandler = (response: AxiosResponse<any, any>) => {
        const { result, message, data } = response.data as ResponseDto<GetMyLikeListResponseDto[]>;

        if (!result || !data) {
            alert(message);
            return;
        }

        setMyList(data);
    }

    const onPatchProfileUploadResponseHandler = (response: AxiosResponse<any, any>) => {
        const profileUrl = response.data as string;
        const data: PatchProfileRequestDto = { profileUrl };

        axios.patch(PATCH_PROFILE_URL, data, authorizationHeader(accessToken))
        .then((response) => onPatchProfileResponseHandler(response))
        .catch((error) => onPatchProfileErrorHandler(error));
    }

    const onPatchProfileResponseHandler = (response: AxiosResponse<any, any>) => {
        const { result, message, data } = response.data as ResponseDto<PatchProfileResponseDto>;

        if (!result || !data) {
            console.log(message);
            return;
        }

        setUser(data);
    }

    const getMyListErrorHandler = (error: any) => {
        console.log(error.response);
    }

    const getMyLikeListErrorHandler = (error: any) => {
        console.log(error.response);
    }

    const onPatchProfileUploadErrorHandler = (error: any) => {
        console.log(error.response);
    }

    const onPatchProfileErrorHandler = (error: any) => {
        console.log(error.response);
    }
    
    useEffect(() => {
        if (!isLoad) {
            isLoad = true;
            return;
        }

        if (!accessToken) {
            alert('로그인 후 사용 가능합니다.');
            navigator('/');
        }
        getMyList();
    },[])
    
    useEffect(() => {

    },[viewFlag])

    
    return(
        <Box sx={{display: 'flex', justifyContent: 'center'}}>
            <Box width='500px' sx={{display: 'flex', flexDirection: 'column', mt:'50px'}}>
                <Typography sx={{fontWeight:'700'}}>My page</Typography>
                <Box sx={{display:'flex', mt: '20px'}}>
                    <Avatar sx={{width: '130px', height: '130px'}} src={user?.profile ? user.profile : ''} onClick={() => onPatchProfileButtonHandler()} />
                    <input ref={imageRef} hidden type="file" accept="image/*" onChange={(event) => onPatchProfileUploadHandler(event)} />
                    <Box sx={{ml:'50px', display:'flex', justifyContent:'center', flexDirection:'column'}}>
                        <Box>
                            <Typography sx={{fontSize:'20px', fontWeight:'700'}}>{user?.nickname}</Typography>
                        </Box>
                        <Box sx={{display:'flex', mt:'5px'}}>
                            <Typography sx={{mr:'10px'}}>{user?.height}</Typography>
                            <Typography sx={{mr:'10px'}}>{user?.weight}</Typography>
                            { user?.gender === '남' ? (<MaleIcon sx={{color:'blue'}} />) : (<FemaleIcon sx={{color:'pink'}} />) }
                        </Box>
                    </Box>
                </Box>

                <Box sx={{mt:'30px', display:'flex', justifyContent:'space-around'}}>
                    <IconButton onClick={onViewMyList}>
                        { viewFlag === 1 ? (<GridViewRoundedIcon/>) : (<GridViewIcon />) }
                    </IconButton>                        
                    <IconButton onClick={onViewMyLikeList}>
                        { viewFlag === 2 ? (<FavoriteIcon />) : (<FavoriteBorderIcon />) }    
                    </IconButton>                        
                </Box>

                <Box>
                    <ImgListItem item={myList} />
                </Box>
            </Box>
        </Box>
    )
}