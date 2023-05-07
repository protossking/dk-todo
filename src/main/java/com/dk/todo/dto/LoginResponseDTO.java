package com.dk.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResponseDTO {

    private String email;
    private String accessToken;
    private String refreshToken;


}
