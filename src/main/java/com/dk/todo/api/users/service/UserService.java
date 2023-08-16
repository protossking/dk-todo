package com.dk.todo.api.users.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.dk.todo.api.users.request.UserDTO;
import com.dk.todo.config.jwt.JwtTokenProvider;
import com.dk.todo.domain.users.UserRepository;
import com.dk.todo.domain.users.Users;
import com.dk.todo.utils.FileUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
public class UserService {


    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    private final JwtTokenProvider jwtTokenProvider;
    private final BCryptPasswordEncoder encoder;
    private final UserRepository userRepository;
    private final FileUtils fileUtils;


    public UserService(BCryptPasswordEncoder encoder, UserRepository userRepository, AuthenticationManagerBuilder authenticationManagerBuilder, JwtTokenProvider jwtTokenProvider, AmazonS3Client amazonS3Client, FileUtils fileUtils) {

        this.encoder = encoder;
        this.userRepository = userRepository;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.fileUtils = fileUtils;

    }

    public String login(String email, String password) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);

        // 검증
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 검증된 인증 정보로 JWT 토큰 생성
        return jwtTokenProvider.generateToken(authentication);

    }

//    public Long signup(SignupForm signupForm) {
//        boolean check = checkEmailExists(signupForm.getEmail());
//
//        if (check) {
//            throw new IllegalArgumentException("이미 존재하는 유저입니다.");
//        }
//
//        String encPwd = encoder.encode(signupForm.getPassword());
//
//        Users user = userRepository.save(signupForm.toEntity(encPwd));
//
//        if(user!=null) {
//            return user.getId();
//        }
//        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//    }

    public boolean checkEmailExists(String email) {
        return userRepository.existsUsersByEmail(email);
    }


    @Transactional
    public UserDTO.UserUpdateResponse updateUser(UserDTO.UserUpdateRequest userUpdateRequest, MultipartFile multipartFile, Long userId) {

        Users findUser = userRepository.findById(userId).get();

        if(!ObjectUtils.isEmpty(multipartFile)) {

            String fileUrl = fileUtils.fileUpload(multipartFile, userId);

            findUser.updateUserWithImageFile(userUpdateRequest, fileUrl);
        }
        else {
            findUser.updateUser(userUpdateRequest);
        }

        return new UserDTO.UserUpdateResponse(findUser.getName(), findUser.getTwitterUrl(), findUser.getFacebookUrl(), findUser.getInstagramUrl());
    }


    public UserDTO.UserDetailResponse findUser(Long userId) {
        Users findUser = userRepository.findById(userId).get();

        return new UserDTO.UserDetailResponse(findUser.getId(), findUser.getEmail(), findUser.getFacebookUrl(), findUser.getInstagramUrl(), findUser.getTwitterUrl(), findUser.getName(), findUser.getProfileImg());



    }

}