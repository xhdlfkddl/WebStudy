import { Button, Box, Typography, TextField, Input, InputAdornment, IconButton } from "@mui/material"
import SearchIcon from '@mui/icons-material/Search';

import { useState, ChangeEvent } from "react";

import { useNavigate, useParams } from "react-router-dom";

export default function MainContent() {

    const [ tag, setTag ] = useState<string>('');

    const navigator = useNavigate();

    const onSearchTagChangeHandler = (event: ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {

    }

    return(
        <Box sx={{display: 'flex', justifyContent: 'center'}}>
            <Box width='500px' sx={{display: 'flex', flexDirection: 'column', mt:'50px'}}>
                
                <Box>

                </Box>
                
                <Box>
                    <Input type={'text'} sx={{mb: '15px'}} endAdornment={
                            <InputAdornment position='end'>
                                <IconButton onClick={() => navigator(`/board/search-tag/${tag}`)}>
                                    <SearchIcon />
                                </IconButton>
                            </InputAdornment>
                        }
                        value={tag}
                        onChange={(event) => onSearchTagChangeHandler(event)}
                    />
                </Box>
                
                <Box>

                </Box>

            </Box>
        </Box>
    );
}