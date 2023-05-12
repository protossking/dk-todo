package com.dk.todo.utils;

import com.dk.todo.dto.ResponseDTO;

public class ResponseUtil {

    public static <T> ResponseDTO<T> SUCCESS(String message, T data) {
        return new ResponseDTO<>(ResponseStatus.SUCCESS, message, data);
    }

    public static <T> ResponseDTO<T> SUCCESS(T data) {
        return new ResponseDTO<>(ResponseStatus.SUCCESS, data);
    }

    public static <T> ResponseDTO<T> FAILURE (String message, T data) {
        return new ResponseDTO<>(ResponseStatus.FAILURE, message, data);
    }

    public static <T> ResponseDTO<T> ERROR(String message, T data) {
        return new ResponseDTO<>(ResponseStatus.ERROR, message, data);
    }
}
