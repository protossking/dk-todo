package com.dk.todo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;



@Service
public class MailService {

    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    private final static String inviteSubject = "[Todo Group] 초대 메일 ";

    @Autowired
    public MailService(JavaMailSender mailSender, SpringTemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }

    public void sendInviteMail(String email) {

        Context context = new Context();
        context.setVariable("asdf", "asdf");

        String body = templateEngine.process("invite-template.html", context);
        sendMail(email, inviteSubject, body);


    }

    private void sendMail(String to, String subject, String body) {

        MimeMessagePreparator mimeMessagePreparator = mimeMessage -> {
            final MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            helper.setFrom("noreply@noreply.com");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body, true);
        };

        mailSender.send(mimeMessagePreparator);
    }
}
