package com.example.gymcrm.api.rest.dto.trainer;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TrainerRegistrationRequestDTO {
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String specialization;
}
