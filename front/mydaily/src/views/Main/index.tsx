import { Button, Box, Typography } from "@mui/material"
import { useState } from "react";

import { useNavigate, useParams } from "react-router-dom";

export default function MainContent() {

    const navigator = useNavigate();
    const boardNumber = useState<string>("2");

    return(
        <Box>
            <Button onClick={() => navigator(`/board/2`)}>
                boardDetailView
            </Button>
        </Box>
    );
}