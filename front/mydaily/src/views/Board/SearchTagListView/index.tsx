import { Avatar, Box, Typography, Divider, Input, IconButton, InputAdornment } from "@mui/material";
import SearchIcon from '@mui/icons-material/Search';
import { GetSearchTagResponseDto } from "src/apis/response/board";
import { useState, useEffect } from "react";
import axios, { AxiosResponse } from "axios";
import { SEARCH_TAG_URL } from "src/constants/api";
import { useNavigate, useParams } from "react-router-dom";
import ResponseDto from "src/apis/response";
import ImgListItem from "src/components/ImgListItem";

export default function SearchTagListView () {

    const navigator = useNavigate();

    const { tag }  = useParams();
    const [ tagList, setTagList ] = useState<GetSearchTagResponseDto[]>([]);
    const [ responseData, setResponseData ] = useState<GetSearchTagResponseDto>();

    const getSearchList = () => {
        axios.get(SEARCH_TAG_URL(tag as string))
        .then((response) => getSearchListResponseHandler(response))
        .catch((error) => getSearchListErrorHandler(error));

    }

    const getSearchListResponseHandler = (response: AxiosResponse<any, any>) => {
        const { result, message, data } = response.data as ResponseDto<GetSearchTagResponseDto[]>;

        if (!result || !data) {
            alert(message);
            return;
        }

        setTagList(data);
        data.map((items) => setResponseData(items));
    }

    const getSearchListErrorHandler = (error: any) => {
        console.log(error.response);
    }

    useEffect(() => {
        getSearchList();
    },[])

    return (
        <Box sx={{display: 'flex', justifyContent: 'center'}}>
            <Box width='500px' sx={{display: 'flex', flexDirection: 'column', mt:'50px'}}>
                <Box sx={{display: 'flex', justifyContent: 'center', mb:'10px'}} onClick={() => navigator(`/board/search-tag`)}>
                    <Typography sx={{fontWeight:700, color:'rgba(0, 0, 0, 0.5)', mr:'5px'}}>{"#"+tag}</Typography>
                    <SearchIcon />
                </Box>
                <Divider />
                <Box sx={{mt:'20px', display:'flex', justifyContent:'space-around'}}>
                    <Box>
                        <Avatar sx={{width: '130px', height: '130px'}} src={responseData?.boardImgUrl1 ? responseData.boardImgUrl1 : ''} />
                    </Box>
                    <Box sx={{width:'150px', height:'150px', display:'flex', flexDirection:'column', justifyContent:'center'}}>
                        <Box sx={{display:'flex', justifyContent:'center'}}>
                            <Typography sx={{fontWeight:600}}>{tagList.length+"개의 게시물"}</Typography>
                        </Box>
                    </Box>
                </Box>
                {
                    tagList !== null ? (<ImgListItem item={tagList as GetSearchTagResponseDto[]} />) : 
                                        (<Box sx={{mt:'40px', display:'flex', justifyContent:'center'}}><Typography sx={{fontWeight:700}}>검색결과가 없습니다.</Typography></Box>)
                }
                
            </Box>
        </Box>
    )
}