import { Avatar, Box, Typography, IconButton } from "@mui/material";
import MaleIcon from '@mui/icons-material/Male';
import FemaleIcon from '@mui/icons-material/Female';
import GridViewIcon from '@mui/icons-material/GridView';
import FavoriteBorderIcon from '@mui/icons-material/FavoriteBorder';
import GridViewRoundedIcon from '@mui/icons-material/GridViewRounded';
import FavoriteIcon from '@mui/icons-material/Favorite';
import { GetMyLikeListResponseDto, GetMyListResponseDto } from "src/apis/response/board";
import { useState, useEffect } from "react";
import axios, { AxiosResponse } from "axios";
import { GET_MY_LIKE_LIST, GET_MY_LIST, SEARCH_TAG_URL, authorizationHeader } from "src/constants/api";
import ResponseDto from "src/apis/response";
import ImgListItem from "src/components/ImgListItem";
import { useCookies } from "react-cookie";
import { useUserStore } from "src/stores";

export default function MyPageView() {

    const [cookie] = useCookies();
    const { user } = useUserStore();
    const accessToken = cookie.accessToken;
    const [ myList, setMyList ] = useState<GetMyListResponseDto[]>([]);
    const [ myLikeList, setMyLikeList ] = useState<GetMyLikeListResponseDto[]>([]);
    const [ genderFlag, setGenderFlag ] = useState<Boolean | null>(null);
    const [ viewFlag, setViewFlag ] = useState<number>(1);
    
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
    }
    
    const onViewMyLikeList = () => {
        setViewFlag(2);
        getMyLikeList();
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

        setMyLikeList(data);
    }

    const getMyListErrorHandler = (error: any) => {
        console.log(error.response);
    }

    const getMyLikeListErrorHandler = (error: any) => {
        console.log(error.response);
    }
    
    useEffect(() => {
        getMyList();
    },[])
    
    useEffect(() => {

    },[viewFlag])

    
    return(
        <Box sx={{display: 'flex', justifyContent: 'center'}}>
            <Box width='500px' sx={{display: 'flex', flexDirection: 'column', mt:'50px'}}>
                <Typography sx={{fontWeight:'700'}}>My page</Typography>
                <Box sx={{display:'flex', mt: '20px'}}>
                    <Avatar sx={{width: '130px', height: '130px'}} src={user?.profile ? user.profile : ''} />
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
                    {
                        viewFlag === 1 ? (<ImgListItem item={myList as GetMyListResponseDto[]} />) :
                        viewFlag === 2 ? (<ImgListItem item={myLikeList as GetMyLikeListResponseDto[]} />) : (<></>)
                    }
                    
                </Box>
            </Box>
        </Box>
    )
}