package com.carlosjr.email.email;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record EmailDTO(
        @NotNull @Positive Long ownerId,
        @NotNull @Email String emailRecipient,
        @NotNull @NotBlank String subject,
        @NotNull @NotBlank String textContent,
        Object fileContent
        ) {}
