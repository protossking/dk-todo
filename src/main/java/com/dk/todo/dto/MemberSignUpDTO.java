package com.dk.todo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberSignUpDTO {

    private String email;
    private String password;
    private String nickname;
    private String city;

    public MemberSignUpDTO(String email, String password, String nickname, String city) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.city = city;
    }
}
