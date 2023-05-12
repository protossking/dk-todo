package com.dk.todo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public enum ErrorEnum {


    FAIL_MULTIPART("1001", "첨부파일 에러입니다");

    private String code;
    private String description;




    ErrorEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }
}
