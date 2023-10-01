package com.dk.todo.domain.exception.teams;

public class NotFoundTeamExceptionId extends RuntimeException {

    private static final String MESSAGE = "찾을 수 없는 Team Id 입니다";

    public NotFoundTeamExceptionId() {
        super(MESSAGE);
    }
}
