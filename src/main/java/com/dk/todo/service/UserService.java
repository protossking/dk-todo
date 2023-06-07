package com.dk.todo.service;

import com.dk.todo.config.jwt.JwtTokenProvider;
import com.dk.todo.domain.Users;
import com.dk.todo.domain.dto.SignupForm;
import com.dk.todo.domain.dto.UserDTO;
import com.dk.todo.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional
public class UserService {

    private final BCryptPasswordEncoder encoder;
    private final UserRepository userRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;

    public UserService(BCryptPasswordEncoder encoder, UserRepository userRepository, AuthenticationManagerBuilder authenticationManagerBuilder, JwtTokenProvider jwtTokenProvider) {
        this.encoder = encoder;
        this.userRepository = userRepository;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public String login(String email, String password) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);

        // 검증
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 검증된 인증 정보로 JWT 토큰 생성
        return jwtTokenProvider.generateToken(authentication);

    }

    public Long signup(SignupForm signupForm) {
        boolean check = checkEmailExists(signupForm.getEmail());

        if (check) {
            throw new IllegalArgumentException("이미 존재하는 유저입니다.");
        }

        String encPwd = encoder.encode(signupForm.getPassword());

        Users user = userRepository.save(signupForm.toEntity(encPwd));

        if(user!=null) {
            return user.getId();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    public boolean checkEmailExists(String email) {
        return userRepository.existsUsersByEmail(email);
    }


    @Transactional
    public UserDTO.UserUpdateResponse updateUser(Long userId, UserDTO.UserUpdateRequest userUpdateRequest) {

        Users findUser = userRepository.findById(userId).get();

        findUser.updateUser(userUpdateRequest);

        return new UserDTO.UserUpdateResponse(findUser.getName(), findUser.getTwitterUrl(), findUser.getFacebookUrl(), findUser.getInstagramUrl());
    }

}