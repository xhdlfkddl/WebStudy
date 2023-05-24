import { Box, Typography, TextField, Input, InputAdornment, IconButton, ButtonGroup, ImageListItem, ImageListItemBar, ListSubheader, Button, Stack, styled, Paper, Grid } from "@mui/material"
import SearchIcon from '@mui/icons-material/Search';

import { useNavigate } from "react-router-dom";
import { useState, ChangeEvent, useEffect } from "react";
import axios, { AxiosResponse } from "axios";
import { GET_TOP_15_SEARCH_TAG } from "src/constants/api";
import ResponseDto from "src/apis/response";
import { GetTop15SearchWordResponseDto } from "src/apis/response/board";

export default function SearchTagView () {
    
    const navigator = useNavigate();

    const [ tag, setTag ] = useState<string>('');
    const [ tagList, setTagList ] = useState<string[]>([]);
    const [ openTagListView, setOpenTagListView ] = useState<Boolean>(false);

    let isLoad = false;

    const onSearchTagChangeHandler = (event: ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
        const value = event.target.value;
        setTag(value);
    }

    const getTop15SearchTagListHandler = () => {
        axios.get(GET_TOP_15_SEARCH_TAG)
        .then((response) => getTop15SearchTagListResponseHandler(response))
        .catch((error) => getTop15SearchTagListErrorHandler(error));
    }

    const openPopularTagList = () => {
        setOpenTagListView(true);
    }

    const getTop15SearchTagListResponseHandler = (response: AxiosResponse<any, any>) => {
        const { result, message, data } = response.data as ResponseDto<GetTop15SearchWordResponseDto>
        
        if (!result || !data) {
            alert(message);
            return;
        }
        setTagList(data.top15SearchWordList);
    }

    const getTop15SearchTagListErrorHandler = (error: any) => {
        console.log(error.response);
    }

    useEffect(() => {
        if (!isLoad) {
            isLoad = true;
            return;
        }

        getTop15SearchTagListHandler();
    },[])

    return (
        <Box sx={{display: 'flex', justifyContent: 'center'}}>
            <Box width='500px' sx={{display: 'flex', flexDirection: 'column', mt:'50px'}}>
                <Box sx={{mt:'10px', display:'flex', justifyContent:'center', flexDirection:'column'}}>
                    <Box sx={{display:'flex', justifyContent:'center'}}>
                        <Input type={'text'} sx={{mb: '15px'}} endAdornment={
                            <InputAdornment position='end'>
                                    <IconButton onClick={() => navigator(`/board/search-tag/${tag}`)}>
                                        <SearchIcon />
                                    </IconButton>
                                </InputAdornment>
                            }
                            value={tag}
                            placeholder="태그를 검색해보세요"
                            onChange={(event) => onSearchTagChangeHandler(event)}
                            onClick={() => openPopularTagList()}
                            />
                    </Box>
                    <Box>
                        <Typography sx={{mt:'10px', fontSize:'13px', color:'rgba(0, 0, 0, 0.5)'}}>인기 태그</Typography>
                        <Grid container spacing={1} sx={{mt:'5px'}}>
                                {
                                    tagList.map((tag) => (<Grid item xs={2}><Button size="small" onClick={() => navigator(`/board/search-tag/${tag}`)}>{tag}</Button></Grid>))
                                }
                        </Grid>
                    </Box>
            
                </Box>
            </Box>
        </Box>
    )
}