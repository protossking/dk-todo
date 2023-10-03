package com.dk.todo.domain.exception.teams;

public class CannotDeleteTeamException extends RuntimeException{

    private static final String MESSAGE = "삭제는 그룹장만 가능합니다.";

    public CannotDeleteTeamException() {
        super(MESSAGE);
    }
}
