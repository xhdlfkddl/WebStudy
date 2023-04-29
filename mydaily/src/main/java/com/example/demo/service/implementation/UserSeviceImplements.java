package com.example.demo.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.common.constant.ResponseMessage;
import com.example.demo.dto.request.user.PatchProfileDto;
import com.example.demo.dto.response.ResponseDto;
import com.example.demo.dto.response.user.PatchProfileResponseDto;
import com.example.demo.entity.BoardEntity;
import com.example.demo.entity.CommentEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.BoardRepository;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

@Service
public class UserSeviceImplements implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public ResponseDto<PatchProfileResponseDto> patchProfile(String email, PatchProfileDto patchProfileDto) {
        PatchProfileResponseDto data = null;

        try {
            UserEntity userEntity = userRepository.findByEmail(email);
            if (userEntity == null) return ResponseDto.setFail(ResponseMessage.NOT_EXIST_USER);

            String userProfile = patchProfileDto.getProfileUrl();
            userEntity.patchProfile(userProfile);
            userRepository.save(userEntity);

            List<BoardEntity> boardEntityList = boardRepository.findByWriterEmail(email);
            int boardEntitySize = boardEntityList.size();
            BoardEntity boardEntity = null;
            for (int i = 0; i < boardEntitySize; i++) {
                boardEntity = boardEntityList.get(i);
                boardEntity.patchProfile(userProfile);
                boardRepository.save(boardEntity);
            }

            List<CommentEntity> commentEntityList = commentRepository.findByWriterEmail(email);
            int commentEntitySize = commentEntityList.size();
            CommentEntity commentEntity = null;
            for (int i = 0; i < commentEntitySize; i++) {
                commentEntity = commentEntityList.get(i);
                commentEntity.patchProfile(userProfile);
                commentRepository.save(commentEntity);
            }

            data = new PatchProfileResponseDto(true);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFail(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(data);
    }
    
}
