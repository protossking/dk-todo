package com.dk.todo.event;


import com.dk.todo.service.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class InviteEventHandler {

    /*
    메일 서비스
     */
    private final MailService mailService;

    @EventListener
    public void sendInviteMails(InvitedEvent event) {

        /*
        메일 전송
         */
        mailService.sendInviteMail(event.getInviteCode(), event.getEmails(), event.getTeamName());
    }
}
