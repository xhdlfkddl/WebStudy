import { Box, Divider, Typography, Link } from "@mui/material"
import { useState, ChangeEvent } from "react";
import { Product } from "src/interfaces";

interface Props{
    item: Product;
}

export default function ProductListItem({item} : Props) {
    return (
        <Box sx={{display:'flex', justifyContent:'center', flexDirection:'column', pl:'10px', mt:'10px'}}>
            <Box sx={{display:'flex', mt:'10px'}}>
                <Box sx={{width:'100px'}} component='img' src={item?.productImgUrl ? item.productImgUrl : ''}></Box>
                <Box sx={{display:'flex', justifyContent:'space-around', flexDirection:'column', ml:'10px'}}>
                    <Typography>{item.productName}</Typography>
                    <Typography>{item.productPrice}원</Typography>
                    <Link href={item.productUrl ? item.productUrl : ""}>구매하기</Link>
                </Box>
            </Box>
        </Box>
    )
}