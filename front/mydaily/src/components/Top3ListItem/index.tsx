import { Box, ImageList, ImageListItem, ImageListItemBar } from "@mui/material"
import { GetTop3ListResponseDto } from "src/apis/response/board"
import { useNavigate } from "react-router-dom";

interface Props {
    item: GetTop3ListResponseDto[];
}

export default function Top3ListItem ({item}: Props) {

    const navigator = useNavigate();

    return (
        <ImageList sx={{ width: '470px', height: '100%' }} cols={3}>
                {
                    item.map((item) => (
                        <ImageListItem key={item.boardImgUrl} sx={{display:'block'}}>
                            <Box
                                sx={{ width: '150px', height: '150px', objectFit: 'cover', objectPosition: '0 0' }}
                                component='img'
                                src={`${item.boardImgUrl}?w=150&h=150&fit=crop&auto=format`}
                                srcSet={`${item.boardImgUrl}?w=150&h=150&fit=crop&auto=format&dpr=2 2x`}
                                alt={item.boardImgUrl}
                                loading="lazy"
                                onClick={() => navigator(`/board/${item.boardNumber}`)}
                                />
                            <ImageListItemBar
                                title={item.writerNickname}
                                subtitle={item.boardContent}
                            />
                            </ImageListItem>
                        ))
                    }
                    </ImageList>
    )
}