package com.dk.todo.domain.exception.invitations;

public class NotFoundInvitationId extends RuntimeException {

    private static final String MESSAGE = "찾을 수 없는 Invitation Id 입니다.";

    public NotFoundInvitationId() {
        super(MESSAGE);
    }
}
