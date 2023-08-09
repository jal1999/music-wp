package com.music.app.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import io.github.cdimascio.dotenv.Dotenv;

import java.util.Properties;

@Configuration
public class EmailConfiguration {
    @Bean
    public JavaMailSender getEmailSender() {
        Dotenv envRetriever = Dotenv
                .configure()
                .directory("/Users/jamielafarr/dev/full-stack/music-wp/app")
                .filename(".env")
                .load();
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername(envRetriever.get("EMAIL")); // this can't be spoofed
        mailSender.setPassword(envRetriever.get("GMAIL_PASSWORD"));

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }
}