package com.dk.todo.api.invitation.service;

import com.dk.todo.domain.exception.invitations.NotCorrectInviteCodeException;
import com.dk.todo.domain.exception.invitations.NotFoundInvitationId;
import com.dk.todo.domain.invitations.Invitations;
import com.dk.todo.domain.invitations.InvitationRepository;
import com.dk.todo.domain.teams.TeamRepository;
import com.dk.todo.event.InvitedEvent;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class InvitationService {

    private final ApplicationEventPublisher publisher;

    private final InvitationRepository invitationRepository;

    private final TeamRepository teamRepository;


    @Transactional
    public void sendInvitation(Long senderId, List<String> toEmail, Long teamId) {

        String inviteCode = RandomStringUtils.random(20, 33, 125, false, false);

        String teamName = teamRepository.findById(teamId).orElseThrow(() -> new IllegalArgumentException("")).getTeamName();


        publisher.publishEvent(new InvitedEvent(inviteCode, toEmail, teamName));

        List<Invitations> invitations = toEmail.stream().map(s ->
                        Invitations.builder()
                                .senderId(senderId)
                                .toEmail(s)
                                .inviteCode(inviteCode)
                                .teamId(teamId)
                                .build())
                .collect(Collectors.toList());

        for(Invitations i: invitations) {
            if(invitationRepository.existsBySenderIdAndToEmailAndTeamId(i.getSenderId(), i.getToEmail(), i.getTeamId())) {
                Long invitationId = invitationRepository.findBySenderIdAndToEmailAndTeamId(i.getSenderId(), i.getToEmail(), i.getTeamId())
                        .orElseThrow(NotFoundInvitationId::new)
                        .getId();
                invitationRepository.renewInviteCode(inviteCode, invitationId);
            }
            else {
                invitationRepository.save(i);
            }
        }

    }

    public Long findTeamId(String inviteCode) {

        return invitationRepository.findByInviteCode(inviteCode)
                .orElseThrow(NotCorrectInviteCodeException::new)
                .getTeamId();

    }
}
