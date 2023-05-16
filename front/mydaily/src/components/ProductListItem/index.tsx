import { Avatar, Box, Divider, FormControl, OutlinedInput, Typography, Button, Link } from "@mui/material"
import { useState, ChangeEvent } from "react";

export default function ProductListItem() {
    return (
        <Box sx={{display:'flex', justifyContent:'center', flexDirection:'column', pl:'10px', mt:'10px'}}>
            <Divider sx={{mb:'10px'}} />
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
    )
}