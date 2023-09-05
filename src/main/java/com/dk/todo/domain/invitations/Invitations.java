package com.dk.todo.domain.invitations;

import com.dk.todo.domain.BaseEntity;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class Invitations extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sender_id")
    private Long senderId;

    @Column(name = "to_email")
    private String toEmail;

    @Column(name = "invite_code")
    private String inviteCode;

    @Column(name = "team_id")
    private Long teamId;

    public Invitations() {

    }

    @Builder
    public Invitations(Long senderId, String toEmail, String inviteCode, Long teamId) {
        this.senderId = senderId;
        this.toEmail = toEmail;
        this.inviteCode = inviteCode;
        this.teamId = teamId;
    }

    public Invitations toEntity(Long senderId, String toEmail, String inviteCode, Long teamId) {
        return Invitations.builder()
                .senderId(senderId)
                .toEmail(toEmail)
                .inviteCode(inviteCode)
                .teamId(teamId)
                .build();
    }


}
