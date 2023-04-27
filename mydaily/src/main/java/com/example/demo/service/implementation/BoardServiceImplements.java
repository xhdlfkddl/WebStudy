package com.example.demo.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.common.constant.ResponseMessage;
import com.example.demo.dto.request.board.PostBoardDto;
import com.example.demo.dto.response.ResponseDto;
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
    
}
