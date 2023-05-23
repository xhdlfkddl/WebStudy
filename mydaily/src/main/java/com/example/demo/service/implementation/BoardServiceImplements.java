package com.example.demo.service.implementation;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.common.constant.ResponseMessage;
import com.example.demo.dto.request.board.LikeDto;
import com.example.demo.dto.request.board.PatchBoardDto;
import com.example.demo.dto.request.board.PostBoardDto;
import com.example.demo.dto.request.board.PostCommentDto;
import com.example.demo.dto.response.ResponseDto;
import com.example.demo.dto.response.board.DeleteBoardResponseDto;
import com.example.demo.dto.response.board.GetBoardResponseDto;
import com.example.demo.dto.response.board.GetListResponseDto;
import com.example.demo.dto.response.board.GetSearchTagResponseDto;
import com.example.demo.dto.response.board.GetTop3ListResponseDto;
import com.example.demo.dto.response.board.LikeResponseDto;
import com.example.demo.dto.response.board.MyLikeListResponseDto;
import com.example.demo.dto.response.board.PatchBoardResponseDto;
import com.example.demo.dto.response.board.PostBoardResponseDto;
import com.example.demo.dto.response.board.PostCommentResponseDto;
import com.example.demo.dto.response.board.PostMyListResponseDto;
import com.example.demo.entity.BoardEntity;
import com.example.demo.entity.BoardHasProductEntity;
import com.example.demo.entity.CommentEntity;
import com.example.demo.entity.LikyEntity;
import com.example.demo.entity.ProductEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.BoardHasProductRepository;
import com.example.demo.repository.BoardRepository;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.LikyRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.BoardService;

@Service
public class BoardServiceImplements implements BoardService{

    @Autowired UserRepository userRepository;
    @Autowired BoardRepository boardRepository;
    @Autowired ProductRepository productRepository;
    @Autowired CommentRepository commentRepository;
    @Autowired LikyRepository likyRepository;
    @Autowired BoardHasProductRepository boardHasProductRepository;

    //? 게시물 작성
    @Override
    public ResponseDto<PostBoardResponseDto> postBoard(String email, PostBoardDto postBoardDto) {
        PostBoardResponseDto data = null;

        String userEmail = email;

        try {
            UserEntity userEntity = userRepository.findByEmail(userEmail);
            if (userEntity == null) return ResponseDto.setFail(ResponseMessage.NOT_EXIST_USER);
            
            BoardEntity boardEntity = new BoardEntity(userEntity, postBoardDto);
            boardRepository.save(boardEntity);

            int boardNumber = boardEntity.getBoardNumber();

            List<CommentEntity> commentEntity = commentRepository.findByBoardNumberOrderByWriterDateDesc(boardNumber);
            List<LikyEntity> likyEntity = likyRepository.findByBoardNumber(boardNumber);

            data = new PostBoardResponseDto(boardEntity, commentEntity, likyEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFail(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(data);
    }
    
    //? 게시물 수정
    @Override
    public ResponseDto<PatchBoardResponseDto> patchBoard(String email, PatchBoardDto patchBoardDto) {
        PatchBoardResponseDto data = null;

        int boardNumber = patchBoardDto.getBoardNumber();
        
        try {
            BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
            if (boardEntity == null) return ResponseDto.setFail(ResponseMessage.NOT_EXIST_BOARD);
            
            boolean isMatch = email.equals(boardEntity.getWriterEmail());
            if (!isMatch) return ResponseDto.setFail(ResponseMessage.NOT_PERMISSION);

            List<CommentEntity> commentEntity = commentRepository.findByBoardNumberOrderByWriterDateDesc(boardNumber);
            List<LikyEntity> likyEntity = likyRepository.findByBoardNumber(boardNumber);

            boardEntity.patch(patchBoardDto);
            boardRepository.save(boardEntity);

            data = new PatchBoardResponseDto(boardEntity, commentEntity, likyEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFail(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(data);
    }
    
    //? 좋아요 한 게시물 가져오기
    @Override
    public ResponseDto<List<MyLikeListResponseDto>> myLikeList(String email) {
        List<MyLikeListResponseDto> data = new ArrayList<>();
        MyLikeListResponseDto myLikeListResponseDto = null;
        
        List<LikyEntity> likyEntityList = new ArrayList<>();
        BoardEntity boardEntity = null;
        
        int boardNumber = 0;
        
        try {
            UserEntity userEntity = userRepository.findByEmail(email);
            if (userEntity == null) return ResponseDto.setFail(ResponseMessage.NOT_EXIST_USER);

            likyEntityList = likyRepository.findByUserEmail(email);
            int forSize = likyEntityList.size();

            for (int i = 0; i < forSize; i++) {
                boardNumber = likyEntityList.get(i).getBoardNumber();
                boardEntity = boardRepository.findByBoardNumber(boardNumber);

                int boardEntityNumber = boardEntity.getBoardNumber();
                String boardEntityImgUrl1 = boardEntity.getBoardImgUrl1();
                myLikeListResponseDto = new MyLikeListResponseDto(boardEntityNumber, boardEntityImgUrl1);

                data.add(i, myLikeListResponseDto);
            }


        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFail(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(data);
    }

    //? 태그 검색
    @Override
    public ResponseDto<List<GetSearchTagResponseDto>> searchTag(String tag) {
        List<GetSearchTagResponseDto> data = new ArrayList<>();
        GetSearchTagResponseDto getSearchTagResponseDto = null;
        int boardNumber = 0;
        String boardImgUrl1 = null;

        try {
            List<BoardEntity> boardEntityList = boardRepository.findByTag(tag);
            int boardEntityListSize = boardEntityList.size();
            for(int i = 0; i < boardEntityListSize; i++) {
                boardNumber = boardEntityList.get(i).getBoardNumber();
                boardImgUrl1 = boardEntityList.get(i).getBoardImgUrl1();
                getSearchTagResponseDto = new GetSearchTagResponseDto(boardNumber, boardImgUrl1);
                data.add(i, getSearchTagResponseDto);
            }

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFail(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(data);
    }

    //? 게시물 삭제
    @Override
    public ResponseDto<DeleteBoardResponseDto> deleteBoard(String email, int boardNumber) {
        DeleteBoardResponseDto data = null; 

        try {
            BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
            if (boardEntity == null) return ResponseDto.setFail(ResponseMessage.NOT_EXIST_BOARD);

            boolean isEqualWriter = email.equals(boardEntity.getWriterEmail());
            if (!isEqualWriter) return ResponseDto.setFail(ResponseMessage.NOT_PERMISSION);

            
            List<BoardHasProductEntity> boardHasProductEntityList = boardHasProductRepository.findByBoardNumber(boardNumber);
            int entitySize = boardHasProductEntityList.size();
            for (int i = 0; i < entitySize; i++) {
                BoardHasProductEntity boardHasProductEntity = boardHasProductEntityList.get(i);
                // ProductEntity productEntity = productRepository.findById(boardHasProductEntity.getProductNumber());
                // productRepository.deleteByProductNumber(boardHasProductEntity.getProductNumber());
                boardHasProductRepository.deleteByBoardNumber(boardHasProductEntity.getBoardNumber());
                productRepository.deleteByProductNumber(boardHasProductEntity.getProductNumber());
            }

            commentRepository.deleteByBoardNumber(boardNumber);
            likyRepository.deleteByBoardNumber(boardNumber);

            boardRepository.delete(boardEntity);
            data = new DeleteBoardResponseDto(true);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFail(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(data);
    }

    @Override
    public ResponseDto<GetBoardResponseDto> getBoard(int boardNumber) {

        GetBoardResponseDto data = null;

        try {

            BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
            if (boardEntity == null) return ResponseDto.setFail(ResponseMessage.NOT_EXIST_BOARD);
            UserEntity userEntity = userRepository.findByEmail(boardEntity.getWriterEmail());
            List<LikyEntity> likyList = likyRepository.findByBoardNumber(boardNumber);
            List<CommentEntity> commentList = commentRepository.findByBoardNumberOrderByWriterDateDesc(boardNumber);
            List<BoardHasProductEntity> boardHasProductList = boardHasProductRepository.findByBoardNumber(boardNumber);
            List<ProductEntity> productList = new ArrayList<>();

            for (BoardHasProductEntity boardHasProductEntity : boardHasProductList) {
                int productNumber = boardHasProductEntity.getProductNumber();
                ProductEntity productEntity = productRepository.findById(productNumber);
                productList.add(productEntity);
            }
            
            boardEntity.getViewCount();
            boardRepository.save(boardEntity);

            data = new GetBoardResponseDto(boardEntity, userEntity, commentList, likyList, productList);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFail(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(data);

    }
    @Override
    public ResponseDto<PostCommentResponseDto> postComment(String email, PostCommentDto dto) {
       
        PostCommentResponseDto data = null;

        int boardNumber = dto.getBoardNumber();

        try {
            UserEntity userEntity = userRepository.findByEmail(email);
            if (userEntity == null) return ResponseDto.setFail(ResponseMessage.NOT_EXIST_USER);

            BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
            if (boardEntity == null) return ResponseDto.setFail(ResponseMessage.NOT_EXIST_BOARD);

            CommentEntity commentEntity = new CommentEntity(userEntity, dto);
            commentRepository.save(commentEntity);

            boardEntity.increaseCommentCount();
            boardRepository.save(boardEntity);

            List<CommentEntity> commentList = commentRepository.findByBoardNumberOrderByWriterDateDesc(boardNumber);
            List<LikyEntity> likeList = likyRepository.findByBoardNumber(boardNumber);
            List<BoardHasProductEntity> boardHasProductList = boardHasProductRepository.findByBoardNumber(boardNumber);
            List<ProductEntity> productList = new ArrayList<>();

            for (BoardHasProductEntity boardHasProductEntity : boardHasProductList) {
                int productNumber = boardHasProductEntity.getProductNumber();
                ProductEntity productEntity = productRepository.findById(productNumber);
                productList.add(productEntity);
            }

            data = new PostCommentResponseDto(boardEntity, likeList, commentList, productList);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFail(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(data);
    }

    @Override
    public ResponseDto<LikeResponseDto> like(String email, LikeDto dto) {

        LikeResponseDto data = null;

        int boardNumber = dto.getBoardNumber();

        try {
            UserEntity userEntity = userRepository.findByEmail(email);
            if (userEntity == null) return ResponseDto.setFail(ResponseMessage.NOT_EXIST_USER);

            BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
            if (boardEntity == null) return ResponseDto.setFail(ResponseMessage.NOT_EXIST_BOARD);

            LikyEntity likyEntity = likyRepository.findByUserEmailAndBoardNumber(email, boardNumber);
            if (likyEntity == null) {
                likyEntity = new LikyEntity(userEntity, boardNumber);
                likyRepository.save(likyEntity);
                boardEntity.increaseLikeCount();
            } else {
                likyRepository.delete(likyEntity);
                boardEntity.decreaseLikeCount();
            }
            boardRepository.save(boardEntity);

            List<CommentEntity> commentList = commentRepository.findByBoardNumberOrderByWriterDateDesc(boardNumber);
            List<LikyEntity> likeList = likyRepository.findByBoardNumber(boardNumber);
            List<BoardHasProductEntity> boardHasProductList = boardHasProductRepository.findByBoardNumber(boardNumber);
            List<ProductEntity> productList = new ArrayList<>();

            for (BoardHasProductEntity boardHasProductEntity : boardHasProductList) {
                int productNumber = boardHasProductEntity.getProductNumber();
                ProductEntity productEntity = productRepository.findById(productNumber);
                productList.add(productEntity);
            }

            data = new LikeResponseDto(boardEntity, likeList, commentList, productList);

        } 
        catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFail(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(data);
    }

    @Override
    public ResponseDto<List<PostMyListResponseDto>> getMyList(String email) {
        List<PostMyListResponseDto> data = null;

        try {
            List<BoardEntity> boardList = boardRepository.findByWriterEmailOrderByBoardWriteTimeDesc(email);
            data = PostMyListResponseDto.copyList(boardList);
        }catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFail(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(data);
    }

    @Override
    public ResponseDto<List<GetListResponseDto>> getList() {
        List<GetListResponseDto> data = null;

        try{
            List<BoardEntity> boardEntityList = boardRepository.findByOrderByBoardWriteTimeDesc();
            data = GetListResponseDto.copyList(boardEntityList);

        }catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFail(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(data);
    }

    @Override
    public ResponseDto<List<GetTop3ListResponseDto>> getTop3List() {
        
        List<GetTop3ListResponseDto> data = null;
        Date aWeekAgoDate = Date.from(Instant.now().minus(7, ChronoUnit.DAYS));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String aWeekAgo = simpleDateFormat.format(aWeekAgoDate);
    
        try {
            List<BoardEntity> boardList = boardRepository.findTop3ByBoardWriteTimeGreaterThanOrderByLikeCountDesc(aWeekAgo);
            data = GetTop3ListResponseDto.copyList(boardList);
    
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFail(ResponseMessage.DATABASE_ERROR);
        }
    
        return ResponseDto.setSuccess(data);
    
    }
    
}
