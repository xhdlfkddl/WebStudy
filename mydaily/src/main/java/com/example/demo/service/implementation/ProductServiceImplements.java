package com.example.demo.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.common.constant.ResponseMessage;
import com.example.demo.dto.request.product.PostProductDto;
import com.example.demo.dto.response.ResponseDto;
import com.example.demo.dto.response.product.DeleteProductResponseDto;
import com.example.demo.dto.response.product.PostProductResponseDto;
import com.example.demo.entity.BoardEntity;
import com.example.demo.entity.BoardHasProductEntity;
import com.example.demo.entity.ProductEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.BoardHasProductRepository;
import com.example.demo.repository.BoardRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.ProductService;

@Service
public class ProductServiceImplements implements ProductService {

    @Autowired 
    private UserRepository userRepository;
    @Autowired 
    private ProductRepository productRepository;
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private BoardHasProductRepository boardHasProductRepository;

    @Override
    public ResponseDto<PostProductResponseDto> postProduct(String email, PostProductDto dto) {
        PostProductResponseDto data = null;
        
        try {
            UserEntity userEntity = userRepository.findByEmail(email);
            if (userEntity == null) return ResponseDto.setFail(ResponseMessage.NOT_EXIST_USER);

            ProductEntity productEntity = new ProductEntity(dto);
            productRepository.save(productEntity);

            int productNumber = productEntity.getProductNumber();
            productEntity = productRepository.findById(productNumber);

            data = new PostProductResponseDto(productEntity);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFail(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(data);

    }

    @Override
    public ResponseDto<DeleteProductResponseDto> deleteProduct(String email, int boardNumber, int productNumber) {
        DeleteProductResponseDto data = null;

        try {
            UserEntity userEntity = userRepository.findByEmail(email);
            if (userEntity == null) return ResponseDto.setFail(ResponseMessage.NOT_EXIST_USER);

            BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
            if (boardEntity == null) return ResponseDto.setFail(ResponseMessage.NOT_EXIST_BOARD);

            String userEmail = userEntity.getEmail();
            boolean isMatch = boardEntity.getWriterEmail().equals(userEmail);
            if (!isMatch) return ResponseDto.setFail(ResponseMessage.NOT_PERMISSION);

            ProductEntity productEntity = productRepository.findById(productNumber);
            if (productEntity == null) return ResponseDto.setFail(ResponseMessage.NOT_EXIST_PRODUCT);
            
            BoardHasProductEntity boardHasProductEntity = boardHasProductRepository.findByProductNumber(productNumber);
            if (boardHasProductEntity == null) return ResponseDto.setFail(ResponseMessage.NOT_EXIST_PRODUCT);

            boardHasProductRepository.delete(boardHasProductEntity);
            productRepository.delete(productEntity);

            data = new DeleteProductResponseDto(true);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFail(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(data);
    }
    
}
