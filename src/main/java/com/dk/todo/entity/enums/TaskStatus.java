package com.dk.todo.entity.enums;

import lombok.Getter;

@Getter
public enum TaskStatus {


    TODO("TODO", "할일"),
    DOING("DOING", "진행중"),
    DONE("DONE", "완료");

    private final String code;
    private final String description;

    TaskStatus(String code, String description) {
        this.code = code;
        this.description = description;
    }
}
