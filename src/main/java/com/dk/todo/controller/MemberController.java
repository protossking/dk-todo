package com.dk.todo.controller;

import com.dk.todo.dto.LoginRequestDTO;
import com.dk.todo.dto.MemberSignUpDTO;
import com.dk.todo.dto.ResponseDto;
import com.dk.todo.service.MemberService;
import com.dk.todo.utils.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "회원가입")
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@RestController
public class MemberController {


    private final MemberService memberService;


    @PostMapping(value = "/sign-up")
    public ResponseDto<?> signUp(@RequestBody MemberSignUpDTO memberSignUpDTO) throws Exception {

        memberService.signUp(memberSignUpDTO);
        return ResponseUtil.SUCCESS(null);
    }

    @Operation(description = "로그인")
    @PostMapping("/login")
    public void login(@RequestBody LoginRequestDTO loginRequestDTO) throws Exception {}



}
