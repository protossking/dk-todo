package com.dk.todo.handler;

import com.dk.todo.dto.ResponseDTO;
import com.dk.todo.utils.ResponseUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartException;

@ControllerAdvice
public class TodoExceptionHandler {


//    @ExceptionHandler(MultipartException.class)
//    public ResponseDTO<?> handleMultipartException() {
//        return new ResponseUtil.FAILURE(null);
//    }


}
