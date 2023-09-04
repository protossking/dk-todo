package com.dk.todo.domain.exception.invitations;

public class NotCorrectInviteCodeException extends IllegalArgumentException{

    private static final String MESSAGE = "잘못된 초대 코드 입니다";
    public NotCorrectInviteCodeException() {
        super(MESSAGE);
    }
}
