package com.carlosjr.email.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@Service
public class EmailService {

    @Autowired
    EmailRepository emailRepository;

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
            System.out.println("Enviado com sucesso!");
            email.setStatus(EmailStatus.DELIVERED);
        } catch (Exception ex){
            email.setStatus(EmailStatus.ERROR);
        } finally {
            generatedId = emailRepository.save(email).getId();
        }
        return generatedId;
    }

}
