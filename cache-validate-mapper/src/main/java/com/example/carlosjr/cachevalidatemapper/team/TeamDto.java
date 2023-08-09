package com.example.carlosjr.cachevalidatemapper.team;

import jakarta.validation.constraints.*;
import lombok.Builder;

@Builder
public record TeamDto (
        @Null
        Integer id,
        @NotBlank
        @Size(min=4, max=50)
        @Pattern(regexp = "^[a-zA-ZÀ-ÿ]+$", message = "Name should only contain letters")
        String name,
        @PositiveOrZero
        @NotNull
        Integer titles,
        @NotNull
        Boolean isBig ){
}
