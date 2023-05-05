package com.dk.todo.controller;

import com.dk.todo.entity.dto.MemberSignUpDTO;
import com.dk.todo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/members")
@RestController
public class MemberController {


    private final MemberService memberService;


    @PostMapping(value = "/sign-up")
    public ResponseEntity<?> signUp(@RequestBody MemberSignUpDTO memberSignUpDTO) throws Exception {

        memberService.signUp(memberSignUpDTO);

        return new ResponseEntity<>(HttpStatus.OK);
    }


}
