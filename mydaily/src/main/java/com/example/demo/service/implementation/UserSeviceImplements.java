package com.example.demo.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.common.constant.ResponseMessage;
import com.example.demo.dto.request.user.PatchProfileDto;
import com.example.demo.dto.request.user.ValidateEmailDto;
import com.example.demo.dto.request.user.ValidateNicknameDto;
import com.example.demo.dto.response.ResponseDto;
import com.example.demo.dto.response.user.GetUserResponseDto;
import com.example.demo.dto.response.user.PatchProfileResponseDto;
import com.example.demo.dto.response.user.ValidateEmailResponseDto;
import com.example.demo.dto.response.user.ValidateNicknameResponseDto;
import com.example.demo.entity.BoardEntity;
import com.example.demo.entity.CommentEntity;
import com.example.demo.entity.LikyEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.BoardRepository;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.LikyRepository;
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
    @Autowired
    private LikyRepository likyRepository;

    @Override
    public ResponseDto<GetUserResponseDto> getUser(String email) {

        GetUserResponseDto data = null;

        try {
            UserEntity userEntity = userRepository.findByEmail(email);
            if(userEntity == null)
            return ResponseDto.setFail(ResponseMessage.NOT_EXIST_USER);
            data = new GetUserResponseDto(userEntity);
        }catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFail(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(data);
    }

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

            List<LikyEntity> likyEntityList = likyRepository.findByUserEmail(email);
            int likyEntitySize = likyEntityList.size();
            LikyEntity likyEntity = null;
            for (int i = 0; i < likyEntitySize; i++) {
                likyEntity = likyEntityList.get(i);
                likyEntity.patchProfile(userProfile);
                likyRepository.save(likyEntity);
            }

            data = new PatchProfileResponseDto(true);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFail(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(data);
    }

    @Override
    public ResponseDto<ValidateEmailResponseDto> validateEmail(ValidateEmailDto validateEmailDto) {
        ValidateEmailResponseDto data = null;

        String email = validateEmailDto.getEmail();

        try {
            boolean isExists = userRepository.existsByEmail(email);
            if (isExists) {
                data = new ValidateEmailResponseDto(true);
            } else {
                data = new ValidateEmailResponseDto(false);
            }

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFail(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(data);
    }

    @Override
    public ResponseDto<ValidateNicknameResponseDto> validateNickname(ValidateNicknameDto validateNicknameDto) {
        ValidateNicknameResponseDto data = null;

        String nickname = validateNicknameDto.getNickname();

        try {
            boolean isExists = userRepository.existsByNickname(nickname);
            if (isExists) {
                data = new ValidateNicknameResponseDto(true);
            } else {
                data = new ValidateNicknameResponseDto(false);
            }
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFail(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(data);
    }
    
}
