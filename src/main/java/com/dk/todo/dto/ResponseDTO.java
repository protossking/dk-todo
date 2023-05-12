package com.dk.todo.dto;

import com.dk.todo.utils.ResponseStatus;
import lombok.Getter;

@Getter
public class ResponseDTO<T> {

    private ResponseStatus status;
    private String message;
    private T data;


    public ResponseDTO() {
    }
    public ResponseDTO(ResponseStatus status, T data) {
        this.status = status;
        this.data = data;
    }
    public ResponseDTO(ResponseStatus status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
}
