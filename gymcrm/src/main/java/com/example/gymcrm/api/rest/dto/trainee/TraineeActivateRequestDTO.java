package com.example.gymcrm.api.rest.dto.trainee;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TraineeActivateRequestDTO {
    @NotNull
    private String username;
    @NotNull
    private Boolean isActive;
}
