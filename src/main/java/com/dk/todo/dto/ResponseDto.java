package com.dk.todo.dto;

import com.dk.todo.utils.ResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class ResponseDto<T> {

    private ResponseStatus status;
    private String message;
    private T data;


    public ResponseDto() {
    }
    public ResponseDto(ResponseStatus status, T data) {
        this.status = status;
        this.data = data;
    }
    public ResponseDto(ResponseStatus status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
}
