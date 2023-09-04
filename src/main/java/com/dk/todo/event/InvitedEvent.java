package com.dk.todo.event;

import java.util.List;

public class InvitedEvent{

    private String inviteCode;
    private List<String> emails;

    private String teamName;

    public InvitedEvent(String inviteCode, List<String> emails, String teamName) {
        this.inviteCode = inviteCode;
        this.emails = emails;
        this.teamName = teamName;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public List<String> getEmails() {
        return emails;
    }
}
