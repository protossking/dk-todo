package com.dk.todo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {


    @Value("${spring.mail.host}")
    private String host;

    @Value("${spring.mail.port}")
    private int port;

    @Value("${spring.mail.username}")
    private String username;

    @Value("${spring.mail.password}")
    private String password;

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost(host);
        mailSender.setPort(port);
        mailSender.setUsername(username);
        mailSender.setPassword(password);

        mailSender.setJavaMailProperties(getMailProperties());

        return mailSender;
    }

    private Properties getMailProperties() {

        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp"); // 프로토콜 설정
        props.setProperty("mail.smtp.auth", "true"); // smtp 인증
        props.setProperty("mail.smtp.starttls.enable", "true"); // smtp strattles 사용
        props.setProperty("mail.debug", "true"); // 디버그 사용
        props.setProperty("mail.smtp.ssl.trust","smtp.naver.com"); // ssl 인증 서버는 smtp.naver.com
        props.setProperty("mail.smtp.ssl.enable","true"); // ssl 사용

        return props;
    }
}
