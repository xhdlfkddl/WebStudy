import { Avatar, Box, Divider, IconButton, Button, FormControl, Typography, OutlinedInput, Stack, Pagination, Menu, MenuItem } from "@mui/material"

import MoreVertIcon from '@mui/icons-material/MoreVert';
import FavoriteIcon from '@mui/icons-material/Favorite';
import FavoriteBorderIcon from '@mui/icons-material/FavoriteBorder';
import ModeCommentIcon from '@mui/icons-material/ModeComment';
import ModeCommentOutlinedIcon from '@mui/icons-material/ModeCommentOutlined';
import { useEffect, useState, ChangeEvent, MouseEvent } from "react";
import { Board, Comment, Liky, Product, User } from "src/interfaces";
import axios, { AxiosResponse } from "axios";
import { DELETE_BOARD_URL, GET_BOARD_URL, LIKE_URL, POST_COMMENT_URL, authorizationHeader } from "src/constants/api";
import { useParams, useNavigate } from "react-router-dom";
import { DeleteBoardResponseDto, GetBoardResponseDto, LikeResponseDto, PostCommendResponseDto } from "src/apis/response/board";
import ResponseDto from "src/apis/response";
import ProductListItem from "src/components/ProductListItem";
import { useCookies } from "react-cookie";
import { LikeDto, PostCommentDto } from "src/apis/request/board";
import { useUserStore } from "src/stores";
import CommentListItem from "src/components/CommentListItem";
import { usePagingHook } from "src/hooks";
import { getPageCount } from "src/utils";
import BoardImgListItem from "src/components/BoardImgListItem";

export default function BoardDetailView() {

    const navigator = useNavigate();

    const { listItem, viewListItem, setListItem, onPageHandler, pageNumber, COUNT } = usePagingHook(3);
    const [ cookies ] = useCookies();
    const [ board, setBoard ] = useState<Board | null>(null);
    const { user } = useUserStore();
    const [ commentContent, setCommentContent ] = useState<string>('');
    const [ commentBlankFlag, setCommentBlankFlag ] = useState<Boolean>(false);
    const [ writeUser, setWriteUser ] = useState<User>();
    const [ likyList, setLikyList ] = useState<Liky[]>([]);
    const [ productList, setProductList ] = useState<Product[]>([]);

    const [ liky, setLiky ] = useState<Boolean>(false);
    const [ openComment, setOpenComment ] = useState<Boolean>(false);
    const [ anchorElement, setAnchorElement ] = useState<null | HTMLElement>(null);
    const [ menuOpen, setMenuOpen ] = useState<boolean>(false);


    const { boardNumber } = useParams();

    const accessToken = cookies.accessToken;

    const commentBlankCheck = (event: ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
        setCommentContent(event.target.value);
        setCommentBlankFlag(true);
    }

    const setBoardResponse = (data: GetBoardResponseDto) => {
        const { board, user, commentList, likeList, productList } = data;
        setBoard(board);
        setWriteUser(user);
        setListItem(commentList);
        setLikyList(likeList);
        setProductList(productList);
    }

    const getBoard = () => {
        axios.get(GET_BOARD_URL(boardNumber as string))
        .then((response) => getBoardResponseHandler(response))
        .catch((error) => getBoardErrorHandler(error));
    }

    const onLikeHandler = () => {
        if (!accessToken) alert('로그인 후 이용 가능합니다.');

        const data: LikeDto = {boardNumber: parseInt(boardNumber as string)}

        axios.post(LIKE_URL, data, authorizationHeader(accessToken))
        .then((response) => onLikeResponseHandler(response))
        .catch((error) => onLikeErrorHandler(error));
    }

    const onPostCommentHandler = () => {
        if (!accessToken) alert('로그인 후 사용해주세요.');

        const data: PostCommentDto = {
            boardNumber: parseInt(boardNumber as string),
            commentContent
        }

        if (commentContent === null || commentContent.trim().length === 0) {
            alert('댓글을 작성한 후 게시해주세요.')
            setCommentContent('');
            setCommentBlankFlag(false);
            return;
        }

        axios.post(POST_COMMENT_URL, data, authorizationHeader(accessToken))
        .then((response) => onPostCommentResponseHandler(response))
        .catch((error) => onPostCommentErrorHandler(error))
    }

    const onMenuHandler = (event: MouseEvent<HTMLButtonElement>) => {
        setAnchorElement(event.currentTarget);
        setMenuOpen(true);
    }

    const onMenuCloseHandler = () => {
        setAnchorElement(null);
        setMenuOpen(false);
    }

    const onDeleteHandler = () => {
        if (!user) {
            alert('로그인이 필요합니다.'); return;
        }
        if (user?.email !== writeUser?.email) {
            alert('권한이 없습니다.'); return;
        }

        axios.delete(DELETE_BOARD_URL(boardNumber as string), authorizationHeader(accessToken as string))
        .then((response) => onDeleteResponseHandler(response))
        .catch((error) => onDeleteErrorHandler(error));
    }

    // 
    const getBoardResponseHandler = (response: AxiosResponse<any, any>) => {
        const { data, message, result } = response.data as ResponseDto<GetBoardResponseDto>;

        if (!data || !result) {
            alert(message);
            navigator('/');
            return;
        }

        setBoardResponse(data);
    }

    const onLikeResponseHandler = (response: AxiosResponse<any, any>) => {
        const { result, message, data } = response.data as ResponseDto<LikeResponseDto>;

        if (!result || !data) {
            alert(message);
            return;
        }

        setBoardResponse(data);
    }

    const onPostCommentResponseHandler = (response: AxiosResponse<any, any>) => {
        const { result, message, data } = response.data as ResponseDto<PostCommendResponseDto>

        if (!result || !data) {
            alert(message);
            return;
        }

        setBoardResponse(data);
        setCommentContent('');
    }

    const onDeleteResponseHandler = (response: AxiosResponse<any, any>) => {
        const { result, message, data } = response.data as ResponseDto<DeleteBoardResponseDto>;

        if (!result || !data) {
            alert(message);
            return;
        }

        if (data) {
            alert('삭제가 완료되었습니다.');
            navigator('/myPage');
        }
    }

    //
    const getBoardErrorHandler = (error: any) => {
        console.log(error.message);
    }

    const onLikeErrorHandler = (error: any) => {
        console.log(error.message);
    }
    
    const onPostCommentErrorHandler = (error: any) => {
        console.log(error.message);
    }
    
    const onDeleteErrorHandler = (error: any) => {
        console.log(error.message);
    }

    //
    useEffect(() => {
        if(!boardNumber) return;

        getBoard();
    },[])

    useEffect(() => {
        if (!user) return;
        const likeUser = likyList.find((likeUser) => likeUser.userEmail === user.email);
        setLiky(likeUser !== undefined);
    },[likyList])

    return(
        <Box sx={{display: 'flex', justifyContent: 'center'}}>
            <Box width='500px' sx={{display: 'flex', flexDirection: 'column', mt:'50px'}}>

                <Box height='60px' sx={{display:'flex', alignItems:'center', justifyContent:'space-between'}}>
                    <Box sx={{display: "flex", ml:'10px'}}>
                        <Avatar src={writeUser?.profile ? writeUser.profile : "/broken-image.jpg"} />
                        <Box sx={{display:'flex', flexDirection:'column', ml:'15px'}}>
                            <Typography sx={{fontWeight:'700', fontSize:'15px'}}>{board?.writerNickname}</Typography>
                            <Box sx={{display:'flex'}}>
                                <Typography sx={{color:'rgba(0, 0, 0, 0.4)', fontWeight:'700', fontSize:'12px', mr:'7px'}}>{writeUser?.height}</Typography>
                                <Typography sx={{color:'rgba(0, 0, 0, 0.4)', fontWeight:'700', fontSize:'12px'}}>{writeUser?.weight}</Typography>
                            </Box>
                        </Box>
                    </Box>
                    <Box>
                            {
                                user?.email === writeUser?.email ? 
                                (<IconButton onClick={(event) => onMenuHandler(event)}><MoreVertIcon /></IconButton>) : (<></>) 
                            }
                        <Menu anchorEl={anchorElement} open={menuOpen} onClose={onMenuCloseHandler}>
                            <MenuItem sx={{ p: '10px 59px', opacity: 0.5 }} onClick={() => navigator('/')}>수정</MenuItem>
                            <Divider />
                            <MenuItem sx={{ p: '10px 59px', color: '#ff0000', opacity: 0.5 }} onClick={() => onDeleteHandler()}>삭제</MenuItem>
                        </Menu>
                    </Box>
                </Box>
                
                <Box sx={{display:'flex', justifyContent:'center'}}>
                    { board ? (<BoardImgListItem item={board} />) : (<></>) }
                    
                </Box>
                
                <Box height='60px' sx={{display:'flex', alignItems:'center'}}>
                    <IconButton onClick={onLikeHandler}>
                        {
                            liky ? (<FavoriteIcon sx={{ fontSize: '35px', fontWeight: 500, opacity: 0.7, color:'red' }} /> ) :
                                   (<FavoriteBorderIcon sx={{ fontSize: '35px', fontWeight: 500, opacity: 0.7 }} /> )
                        }
                        
                    </IconButton>
                    <IconButton onClick={() => setOpenComment(!openComment)}>
                        {
                            openComment ? (<ModeCommentIcon sx={{ fontSize: '35px', fontWeight: 500, opacity: 0.7, mr: '6px' }} />) :
                                          (<ModeCommentOutlinedIcon sx={{ fontSize: '35px', fontWeight: 500, opacity: 0.7, mr: '6px' }} />)
                        }
                        
                    </IconButton>
                </Box>

                <Box>
                    {
                        openComment ? (
                                    <Box sx={{display: 'flex', ml:'10px'}}>
                                        <Box sx={{display:'flex', width:'490px'}}>
                                            <Avatar sx={{width:'30px', height:'30px', mt:'4px'}} src="/broken-image.jpg" />
                                            <FormControl size="small" fullWidth sx={{ml:'10px'}}>
                                                <OutlinedInput fullWidth onChange={(event) => commentBlankCheck(event)} value={commentContent} placeholder="댓글 달기" />
                                            </FormControl>
                                            {
                                                commentBlankFlag ? (<Button size="small" onClick={onPostCommentHandler}>게시</Button>) : 
                                                                   (<></>)
                                            }
                                        </Box>
                                        
                                    </Box>
                        ) : (<></>)
                    }
                </Box>
                <Box>
                        { openComment ? (
                                        <Box sx={{mt:'20px'}}>
                                            <Box>
                                                <Stack>
                                                    {viewListItem.map((commentItem) => (<CommentListItem item={commentItem as Comment} />))}
                                                </Stack>
                                            </Box>
                                            <Box sx={{ p: '20px 0px', display: 'flex', justifyContent: 'center' }}>
                                                <Pagination page={pageNumber} count={getPageCount(listItem, COUNT)} onChange={(event, value) => onPageHandler(value)} />
                                            </Box>
                                            <Divider />
                                        </Box>
                                        )
                    : (<></>) }
                </Box>
                
                <Box sx={{display:'flex', justifyContent:'center', flexDirection:'column'}}>
                    <Box sx={{display:'flex', mt:'10px'}}>
                        <Typography sx={{ml:'10px', fontWeight:'400'}}>좋아요</Typography>
                        <Typography sx={{ml:'7px', fontWeight:'700'}}>{likyList.length}</Typography>
                        <Typography sx={{fontWeight:'400'}}>개</Typography>
                    </Box>
                    <Box sx={{display:'flex', mt:'5px'}}>
                        <Typography sx={{ml:'10px', fontWeight:'700'}}>{board?.writerNickname}</Typography>
                        <Typography sx={{ml:'10px'}}>{board?.boardContent}</Typography>
                    </Box>
                    <Typography sx={{ml:'10px', color:'rgba(0, 0, 0, 0.4)', fontSize:'13px', mt:'10px'}}>{board?.boardWriteTime}</Typography>
                </Box>
                

                <Box>
                    <Box sx={{pl:'10px', mt:'10px'}}>
                        <Divider sx={{mb:'10px'}} />
                        <Typography sx={{color:'rgba(0, 0, 0, 0.4)'}}>착용제품</Typography>
                    </Box>
                    <Stack>
                        {productList.map((productList) => (<ProductListItem item={productList as Product} />))} 
                    </Stack>
                </Box>

                <Box sx={{display:'flex', justifyContent:'center', flexDirection:'column', pl:'10px', mt:'20px', mb:'40px'}}>
                    <Divider sx={{mb:'10px'}} />
                    <Typography sx={{color:'rgba(0, 0, 0, 0.4)'}}>태그</Typography>
                    <Box sx={{mt:'10px'}}>
                        <Typography sx={{display: 'inline', fontWeight:'700', color:'rgba(0, 0, 0, 0.4)', backgroundColor:'rgba(0, 0, 0, 0.2)', p:'8px', borderRadius:'10px'}}>{board?.tag}</Typography>
                    </Box>
                </Box>



            </Box>
        </Box>
    )
}