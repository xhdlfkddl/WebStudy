package com.taeyoung.mydaily.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.taeyoung.mydaily.common.constant.ResponseMessage;
import com.taeyoung.mydaily.dto.request.auth.SignUpDto;
import com.taeyoung.mydaily.dto.response.ResponseDto;
import com.taeyoung.mydaily.dto.response.auth.SignUpResponseDto;
import com.taeyoung.mydaily.entity.UserEntity;
import com.taeyoung.mydaily.repository.UserRepository;
import com.taeyoung.mydaily.service.AuthService;

@Service
public class AuthServiceImplements implements AuthService {

    @Autowired 
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public ResponseDto<SignUpResponseDto> signUp(SignUpDto signUpDto) {
        SignUpResponseDto data = null;

        String email = signUpDto.getEmail();
        String password = signUpDto.getPassword();
        String nickname = signUpDto.getNickname();

        try {
            boolean hasEmail = userRepository.existsByEmail(email);
            if (hasEmail) return ResponseDto.setFail(ResponseMessage.EXISTS_EMAIL);
            
            boolean hasNickname = userRepository.existsByNickname(nickname);
            if (hasNickname) return ResponseDto.setFail(ResponseMessage.EXISTS_NICKNAME);

            String encodedPassword = passwordEncoder.encode(password);
            signUpDto.setPassword(encodedPassword);

            UserEntity userEntity = new UserEntity(signUpDto);
            userRepository.save(userEntity);

            data = new SignUpResponseDto(true);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFail(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(data);
    }
    
}
