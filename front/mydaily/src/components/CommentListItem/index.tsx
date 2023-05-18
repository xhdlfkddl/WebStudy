import { Avatar, Box, Typography } from "@mui/material"
import { useState, ChangeEvent, useEffect } from "react";
import { Comment } from "src/interfaces";

interface Props {
    item: Comment;
}

export default function CommentListItem({item}: Props ) {

    const [ time, setTime ] = useState<string>('');
    
    const commentTime = () => {

        const dateGap = Date.now() - Date.parse(item.writerDate);
        const minute = Math.floor(dateGap / (1000 * 60));
        const hour = Math.floor(minute / 60);
        const day = Math.floor(hour/24);
        const month = Math.floor(day/30);
        const year = Math.floor(month/12);

        if( 0 <= minute && minute < 61) {
            setTime(minute + "분전");
            return;
        } 
        if (0 < hour && hour < 24) {
            setTime(hour + "시간전")
            return;
        } if ( 0 < day && day < 31) {
            setTime(day + "일전");
            return;
        } if (0 < month && month < 12) {
            setTime(month + "개월전");
            return;
        } if (1 <= year) {
            setTime(year + "년전");
            return;
        }

    }

    useEffect (() => {
        commentTime();
    },[item])
    
    return (
        <Box sx={{display: 'flex', ml:'10px', flexDirection:'column'}}>
            <Box>
                <Box sx={{display:'flex', justifyContent:'center', flexDirection:'column'}}>
                    <Box sx={{display:'flex', mt:'10px'}}>
                        <Avatar sx={{width:'30px', height:'30px'}} src={item.writerProfileUrl ? item.writerProfileUrl : ''} />
                        <Box sx={{display:'flex', mt:'4px'}}>
                            <Typography sx={{ml:'10px', fontWeight:'600', fontSize:'15px'}}>{item.writerNickname}</Typography>
                            <Typography sx={{ml:'10px', fontSize:'13px'}}>{item.commentContent}</Typography>
                            <Typography sx={{ml:'10px', fontSize:'13px'}}>{time}</Typography>
                        </Box>
                    </Box>
                </Box>
            </Box>
        </Box>
    )
}