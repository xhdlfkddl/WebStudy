package com.example.demo.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.common.constant.ResponseMessage;
import com.example.demo.dto.request.product.PostProductDto;
import com.example.demo.dto.response.ResponseDto;
import com.example.demo.dto.response.product.PostProductResponseDto;
import com.example.demo.entity.ProductEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.ProductService;

@Service
public class ProductServiceImplements implements ProductService {

    @Autowired UserRepository userRepository;
    @Autowired ProductRepository productRepository;

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
    
}
