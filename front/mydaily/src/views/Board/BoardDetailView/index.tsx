import { Avatar, Box, FormControl, IconButton, Link, Typography } from "@mui/material"

import MoreVertIcon from '@mui/icons-material/MoreVert';
import FavoriteBorderIcon from '@mui/icons-material/FavoriteBorder';
import ModeCommentOutlinedIcon from '@mui/icons-material/ModeCommentOutlined';
import ArrowForwardIosIcon from '@mui/icons-material/ArrowForwardIos';
import ArrowBackIosNewIcon from '@mui/icons-material/ArrowBackIosNew';

export default function BoardDetailView() {
    return(
        <Box sx={{display: 'flex', justifyContent: 'center'}}>
            <Box width='500px' sx={{display: 'flex', flexDirection: 'column', mt:'50px'}}>

                <Box height='60px' sx={{display:'flex', alignItems:'center', justifyContent:'space-between'}}>
                    <Box sx={{display: "flex", ml:'10px'}}>
                        <Avatar src="/broken-image.jpg" />
                        <Box sx={{display:'flex', flexDirection:'column', ml:'15px'}}>
                            <Typography sx={{fontWeight:'700', fontSize:'15px'}}>hello_123</Typography>
                            <Box sx={{display:'flex'}}>
                                <Typography sx={{color:'rgba(0, 0, 0, 0.4)', fontWeight:'700', fontSize:'12px', mr:'7px'}}>175cm</Typography>
                                <Typography sx={{color:'rgba(0, 0, 0, 0.4)', fontWeight:'700', fontSize:'12px'}}>80kg</Typography>
                            </Box>
                        </Box>
                    </Box>
                    <Box>
                        <IconButton>
                            <MoreVertIcon />
                        </IconButton>
                    </Box>
                </Box>
                
                <Box sx={{display:'flex', justifyContent:'center'}}>
                    <IconButton sx={{top:'20px'}}>
                        <ArrowBackIosNewIcon sx={{fontSize:'35px'}} />
                    </IconButton>
                    <Box sx={{width:'100%', height:'100%'}} component='img' src="https://image.msscdn.net/images/style/detail/33064/detail_33064_2_500.jpg"></Box>
                    <IconButton sx={{top:'20px'}}>
                        <ArrowForwardIosIcon sx={{fontSize:'35px'}} />
                    </IconButton>
                </Box>
                
                <Box height='60px' sx={{display:'flex', alignItems:'center'}}>
                    <IconButton>
                        <FavoriteBorderIcon sx={{ fontSize: '35px', fontWeight: 500, opacity: 0.7 }} /> 
                    </IconButton>
                    <IconButton>
                        <ModeCommentOutlinedIcon sx={{ fontSize: '35px', fontWeight: 500, opacity: 0.7, mr: '6px' }} />
                    </IconButton>
                </Box>
                
                <Box sx={{display:'flex', justifyContent:'center', flexDirection:'column'}}>
                    <Typography sx={{ml:'10px', color:'rgba(0, 0, 0, 0.4)'}}>2023-05-11</Typography>
                    <Box sx={{display:'flex', mt:'10px'}}>
                        <Typography sx={{ml:'10px', fontWeight:'700'}}>hello_123</Typography>
                        <Typography sx={{ml:'10px'}}>라인 디테일의 미니 원피스에 카고 팬츠를 매치해 개성을 더한 캐주얼 룩</Typography>
                    </Box>
                </Box>
                
                <Box sx={{display:'flex', justifyContent:'center', flexDirection:'column', pl:'10px', mt:'10px'}}>
                    <Typography sx={{color:'rgba(0, 0, 0, 0.4)'}}>착용제품</Typography>
                    <Box sx={{display:'flex', mt:'10px'}}>
                        <Box sx={{width:'100px'}} component='img' src='https://image.msscdn.net/images/goods_img/20230105/3007671/3007671_16729847769136_220.jpg'></Box>
                        <Box sx={{display:'flex', justifyContent:'space-around', flexDirection:'column', ml:'10px'}}>
                            <Typography>락 케이크</Typography>
                            <Typography>나일론 트레이닝 카고 2way 조거 - 버건디</Typography>
                            <Typography>97300원</Typography>
                            <Typography></Typography>
                            <Link href="https://www.musinsa.com/app/styles/views/33064">제품 구매 링크</Link>
                        </Box>
                    </Box>
                </Box>

                <Box sx={{display:'flex', justifyContent:'center', flexDirection:'column', pl:'10px', mt:'20px', mb:'40px'}}>
                    <Typography sx={{color:'rgba(0, 0, 0, 0.4)'}}>태그</Typography>
                    <Box sx={{mt:'10px'}}>
                        <Typography sx={{display: 'inline', fontWeight:'700', color:'rgba(0, 0, 0, 0.4)', backgroundColor:'rgba(0, 0, 0, 0.2)', p:'8px', borderRadius:'10px'}}>나일론팬츠</Typography>
                    </Box>
                </Box>



            </Box>
        </Box>
    )
}