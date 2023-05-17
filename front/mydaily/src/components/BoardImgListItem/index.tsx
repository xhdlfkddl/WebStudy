import { Box, IconButton } from "@mui/material";
import ArrowForwardIosIcon from '@mui/icons-material/ArrowForwardIos';
import ArrowBackIosNewIcon from '@mui/icons-material/ArrowBackIosNew';
import { Board } from "src/interfaces";
import { useState, useEffect, MouseEvent } from "react";

interface Props {
    item: Board;
}

export default function BoardImgListItem({item}: Props) {
    
    const [ imgList, setImgList ] = useState<string[]>([]);
    const [ boardImgUrl, setBoardImgUrl ] = useState<string>();
    const [ showFirstImg, setShowFirstImg ] = useState<boolean>(true);
    const [ showSecondImg, setShowSecondImg ] = useState<boolean>(false);
    const [ showThirdImg, setShowThirdImg ] = useState<boolean>(false);
    const [ theFirstImg, setTheFirstImg ] = useState<boolean>(false);
    const [ theLastImg, setTheLastImg ] = useState<boolean>(false);

    const boardImgList: string[] = [];

    // 이거 왜 만들었지
    const imgLength = () => {
        boardImgList.push(item.boardImgUrl1);
        if (item.boardImgUrl2) boardImgList.push(item.boardImgUrl2);
        if (item.boardImgUrl3) boardImgList.push(item.boardImgUrl3);
        setImgList(boardImgList);
    }

    const onNextImg = (event: MouseEvent<HTMLButtonElement, globalThis.MouseEvent>) => {
        if (showFirstImg) {
            setShowFirstImg(false);
            setBoardImgUrl(imgList[1]);
            setShowSecondImg(true);
        }
        if (showSecondImg) {
            setShowSecondImg(false);
            setBoardImgUrl(imgList[2]);
            setShowThirdImg(true);
            setTheLastImg(true);
        }
        
    }
    const onPrevImg = (event: MouseEvent<HTMLButtonElement, globalThis.MouseEvent>) => {
        if (showSecondImg) {
            setShowFirstImg(true);
            setBoardImgUrl(imgList[0]);
            setShowSecondImg(false);
            setTheFirstImg(true);
        }
        if (showThirdImg) {
            setShowSecondImg(true);
            setBoardImgUrl(imgList[1]);
            setShowThirdImg(false);
            setTheLastImg(false);
        }
    }

    useEffect(() => {
        setBoardImgUrl(item.boardImgUrl1);
        imgLength();
    },[])

    return (
        <Box sx={{display:'flex', justifyContent:'center'}}>
            {/* {
                theFirstImg ? (<></>) : 
                            (<IconButton sx={{top:'20px'}} onClick={(event) => onPrevImg(event)}>
                                <ArrowBackIosNewIcon sx={{fontSize:'35px'}} />
                            </IconButton>)
            } */}
            <IconButton sx={{top:'20px'}} onClick={(event) => onPrevImg(event)}>
                <ArrowBackIosNewIcon sx={{fontSize:'35px'}} />
            </IconButton>

            {
                showFirstImg ? (<Box sx={{width:'100%', height:'100%'}} component='img' src={boardImgUrl}/>) : 
                showSecondImg ? (<Box sx={{width:'100%', height:'100%'}} component='img' src={boardImgUrl}/>) : 
                showThirdImg ? (<Box sx={{width:'100%', height:'100%'}} component='img' src={boardImgUrl}/>) : (<></>)
            }

            {/* {
                theLastImg ? (<></>) :
                            (<IconButton sx={{top:'20px'}} onClick={(event) => onNextImg(event)}>
                                <ArrowForwardIosIcon sx={{fontSize:'35px'}} />
                            </IconButton>)
            } */}
            <IconButton sx={{top:'20px'}} onClick={(event) => onNextImg(event)}>
                <ArrowForwardIosIcon sx={{fontSize:'35px'}} />
            </IconButton>
            
        </Box>
    )
}