package com.dk.todo.utils;

import lombok.Getter;

@Getter

public enum ErrorCode {

    JWT_EXPIRE("1001", "Expired Token!");


    private final String code;
    private final String description;

    ErrorCode(String code, String description) {
        this.code = code;
        this.description = description;
    }
}
