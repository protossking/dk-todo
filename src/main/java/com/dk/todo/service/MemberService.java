package com.dk.todo.service;

import com.dk.todo.entity.Member;
import com.dk.todo.entity.dto.MemberSignUpDTO;
import com.dk.todo.entity.enums.Role;
import com.dk.todo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;


    public void signUp(MemberSignUpDTO memberSignUpDTO) throws Exception {

        if(memberRepository.findByEmail(memberSignUpDTO.getEmail()).isPresent()) {
            throw new Exception("이미 존재하는 이메일 입니다");
        }

        if(memberRepository.findByNickname(memberSignUpDTO.getNickname()).isPresent()) {
            throw new Exception("이미 존재하는 닉네임 입니다");
        }

        Member saveMember = Member.builder()
                .email(memberSignUpDTO.getEmail())
                .password(memberSignUpDTO.getPassword())
                .nickname(memberSignUpDTO.getNickname())
                .city(memberSignUpDTO.getCity())
                .role(Role.USER)
                .build();

        saveMember.passwordEncode(passwordEncoder);
        memberRepository.save(saveMember);
    }

}
