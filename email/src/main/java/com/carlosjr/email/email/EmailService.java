package com.carlosjr.email.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import org.springframework.mail.javamail.JavaMailSender;
import java.util.Optional;

@Service
public class EmailService {
    @Autowired
    EmailRepository emailRepository;
    @Autowired
    private JavaMailSender javaMailSender;



    public Email findById(Long emailId) {
        Optional<Email> email = emailRepository.findById(emailId);
        if (email.isPresent())
            return email.get();
        return null;
    }

    public Long sendEmail(Email email){
        Long generatedId = 0L;
        email.setTimeStamp(LocalDateTime.now());
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom("io.carlosjr.spring@gmail.com");
            mailMessage.setTo(email.getEmailRecipient());
            mailMessage.setSubject(email.getSubject());
            mailMessage.setText(email.getTextContent());
            javaMailSender.send(mailMessage);
            email.setErrorCause("Ok");
            email.setStatus(EmailStatus.DELIVERED);
        } catch (MailException mex){
            System.out.println(mex.fillInStackTrace());
            email.setErrorCause(mex.getMessage());
            email.setStatus(EmailStatus.ERROR);
        } finally {
            generatedId = emailRepository.save(email).getId();
        }
        return generatedId;
    }

}
