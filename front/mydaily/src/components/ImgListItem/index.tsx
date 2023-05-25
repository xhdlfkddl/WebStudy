import { Avatar, Box, ImageList, ImageListItem, Typography, Divider } from "@mui/material";
import { useNavigate } from "react-router-dom";
import { GetSearchTagResponseDto, GetMyListResponseDto, GetMyLikeListResponseDto, GetListResponseDto } from "src/apis/response/board";

interface Props {
    item: GetSearchTagResponseDto[] | GetMyListResponseDto[] | GetMyLikeListResponseDto[] | GetListResponseDto[];
}

export default function ImgListItem({item}: Props) {

    const navigator = useNavigate();

    return (
        <ImageList sx={{ width: '470px', height: '100%' }} cols={3}>
        {
            item.map((item) => (
                <Box>
                    <ImageListItem key={item.boardImgUrl1} sx={{display:'block'}}>
                        <Box
                            sx={{ width: '150px', height: '150px', objectFit: 'cover', objectPosition: '0 0' }}
                            component='img'
                            src={`${item.boardImgUrl1}?w=150&h=150&fit=crop&auto=format`}
                            alt={item.boardImgUrl1}
                            onClick={() => navigator(`/board/${item.boardNumber}`)}
                            />
                    </ImageListItem>
                </Box>
                ))
            }
        </ImageList>
    )
    
    }