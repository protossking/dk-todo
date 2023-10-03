package com.dk.todo.domain.exception.teams;

public class CannotExitTeamException extends RuntimeException{

    private static final String MESSAGE = "그룹장은 탈퇴할 수 없습니다.";

    public CannotExitTeamException() {

        super(MESSAGE);
    }
}
