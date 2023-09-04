package com.dk.todo.domain.exception.invitations;

public class NotFoundInvitationId extends RuntimeException {

    public NotFoundInvitationId() {
        super("찾을 수 없는 Invitation Id 입니다.");
    }
}
