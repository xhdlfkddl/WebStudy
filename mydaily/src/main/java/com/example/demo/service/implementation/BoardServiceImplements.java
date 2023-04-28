package com.example.demo.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.common.constant.ResponseMessage;
import com.example.demo.dto.request.board.PatchBoardDto;
import com.example.demo.dto.request.board.PostBoardDto;
import com.example.demo.dto.response.ResponseDto;
import com.example.demo.dto.response.board.PatchBoardResponseDto;
import com.example.demo.dto.response.board.PostBoardResponseDto;
import com.example.demo.entity.BoardEntity;
import com.example.demo.entity.CommentEntity;
import com.example.demo.entity.LikyEntity;
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

            // List<ProductEntity> productEntityList = null;
            
            // List<BoardHasProductEntity> boardHasProductEntity = boardHasProductRepository.findByBoardNumber(boardNumber);
            // for (BoardHasProductEntity number : boardHasProductEntity) {
            //     int productNumber = number.getBoardHasProductPk().getProductNumber();
            //     ProductEntity productEntity = productRepository.findById(productNumber) ;
            //     productEntityList.add(productEntity);
            // }

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
            System.out.println("email: " + email);
            BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
            if (boardEntity == null) return ResponseDto.setFail(ResponseMessage.NOT_EXIST_BOARD);
            
            // String isMatch = boardEntity.getWriterEmail();
            boolean isMatch = email.equals(boardEntity.getWriterEmail());
            System.out.println(isMatch);
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

}
