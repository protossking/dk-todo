package com.dk.todo.domain.exception.users;

public class NotFoundUserIdException extends RuntimeException{

    private static final String MESSAGE = "해당 ID에 맞는 유저를 찾을 수 없습니다.";

    public NotFoundUserIdException() {
        super(MESSAGE);
    }
}
