package com.dk.todo.domain.exception.teams;

public class NotFoundTeamIdException extends RuntimeException {

    private static final String MESSAGE = "찾을 수 없는 Team Id 입니다";

    public NotFoundTeamIdException() {
        super(MESSAGE);
    }
}
