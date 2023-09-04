package com.dk.todo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;


@RequiredArgsConstructor
@Service
public class MailService {

    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    private final static String inviteSubject = "[Todo Group] 초대 메일 ";


    public void sendInviteMail(String code, List<String> emails, String teamName) {


        try {
            for (String email : emails) {
                MimeMessage message = mailSender.createMimeMessage();
                message.addRecipients(MimeMessage.RecipientType.TO, email);
                message.setSubject(inviteSubject);
                message.setFrom("todo_test_1@naver.com");
                message.setText(setContext(code, teamName), "utf-8", "html");
                mailSender.send(message);
            }
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }


    }

    private String setContext(String code, String teamName) {

        Context context = new Context();
        context.setVariable("code", code);
        context.setVariable("teamName", teamName);
        return templateEngine.process("invite-template", context);
    }

}
