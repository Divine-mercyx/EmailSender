package org.EmailNotification.services;

import org.EmailNotification.data.models.EmailDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendMail(EmailDetail emailDetail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailDetail.getRecipient());
        message.setSubject(emailDetail.getSubject());
        message.setText(emailDetail.getBody());
        mailSender.send(message);
    }
}
