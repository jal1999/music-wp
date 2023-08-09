package com.music.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailService {

    private final JavaMailSender emailSender;

    @Autowired
    public EmailService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@maxlafarrmusic.com");
        message.setReplyTo("noreply@maxlafarrmusic.com");
        message.setTo("lafarrjam99@gmail.com");
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }
}