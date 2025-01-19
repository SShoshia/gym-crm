package com.example.gymcrm.api.rest.dto.trainee;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TraineeUpdateProfileDTO {
    @NotNull
    private String username;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    private LocalDate dateOfBirth;
    private String address;
    @NotNull
    private Boolean isActive;
}
