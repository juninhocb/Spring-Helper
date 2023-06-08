package com.carlosjr.email.email;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.io.File;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
public class Email implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long ownerId;
    private String emailRecipient;
    private String subject;
    @Column(columnDefinition = "TEXT")
    private String textContent;
    //private File fileContent;
    @Column(name = "timestamp")
    private LocalDateTime timeStamp;
    private EmailStatus status;
    private String errorCause;
}
