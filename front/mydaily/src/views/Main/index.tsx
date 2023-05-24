import { Box, Typography, TextField, Input, InputAdornment, IconButton, ButtonGroup, ImageListItem, ImageListItemBar, ListSubheader, Button, Stack, styled, Paper, Grid } from "@mui/material"
import SearchIcon from '@mui/icons-material/Search';

import { useState, ChangeEvent, useEffect } from "react";

import { useNavigate, useParams } from "react-router-dom";
import axios, { AxiosResponse } from "axios";
import { GET_LIST, GET_TOP_15_SEARCH_TAG, GET_TOP_3_LIST } from "src/constants/api";
import ResponseDto from "src/apis/response";
import { GetListResponseDto, GetTop15SearchWordResponseDto, GetTop3ListResponseDto } from "src/apis/response/board";
import Top3ListItem from "src/components/Top3ListItem";
import ImgListItem from "src/components/ImgListItem";
import SearchTagView from "src/components/SearchTagView";

export default function MainContent() {

    const navigator = useNavigate();

    const [ top3List, setTop3List ] = useState<GetTop3ListResponseDto[]>([]);
    const [ allList, setAllList ] = useState<GetListResponseDto[]>([]);
    
    let isLoad = false;

    const getTop3ListHandler = () => {
        axios.get(GET_TOP_3_LIST)
        .then((response) => getTop3ListResponseHandler(response))
        .catch((error) => getTop3ListErrorHandler(error));
    }

    const getListHandler = () => {
        axios.get(GET_LIST)
        .then((response) => getListResponseHandler(response))
        .catch((error) => getListErrorHandler(error));
    }

    const getTop3ListResponseHandler = (response: AxiosResponse<any, any>) => {
        const { result, message, data } = response.data as ResponseDto<GetTop3ListResponseDto[]>

        if (!result || !data) {
            alert(message);
            return;
        }

        setTop3List(data);
    }

    const getListResponseHandler = (response: AxiosResponse<any, any>) => {
        const { result, message, data } = response.data as ResponseDto<GetListResponseDto[]>

        if (!result || !data) {
            alert(message);
            return;
        }

        setAllList(data);
    }

    const getTop3ListErrorHandler = (error: any) => {
        console.log(error.response);
    }

    const getListErrorHandler = (error: any) => {
        console.log(error.response);
    }

    useEffect(() => {
        if (!isLoad) {
            isLoad = true;
            return;
        }

        getTop3ListHandler();
        getListHandler();
    },[])

    return(
        <Box sx={{display: 'flex', justifyContent: 'center'}}>
            <Box width='500px' sx={{display: 'flex', flexDirection: 'column', mt:'50px'}}>

                <Typography sx={{fontSize:'20px', fontWeight:'700'}}>How do I look?</Typography>
            
                <Typography sx={{mt:'20px', fontSize:'13px', color:'rgba(0, 0, 0, 0.5)'}}>주간 인기 게시물</Typography>
                <Top3ListItem item={top3List as GetTop3ListResponseDto[]} />

                <Box sx={{mt:'15px', display:'flex', justifyContent:'center'}}>
                <Input type={'text'} sx={{mb: '15px'}} endAdornment={
                    <InputAdornment position='end'>
                            <IconButton >
                                <SearchIcon />
                            </IconButton>
                        </InputAdornment>
                    }
                    value={null}
                    placeholder="태그를 검색해보세요"
                    onClick={() => navigator(`/board/search-tag`)}
                    />
            </Box>
                
                <Typography sx={{mt:'20px', fontSize:'13px', color:'rgba(0, 0, 0, 0.5)'}}>최신 게시물</Typography>
                <ImgListItem item={allList as GetListResponseDto[]} />
            </Box>
        </Box>
    );
}