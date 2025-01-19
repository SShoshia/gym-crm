package com.example.gymcrm.api.rest.dto.trainee;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TraineeRegistrationRequestDTO {
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    private String dateOfBirth;
    private String address;
}
