package com.example.gymcrm.api.rest.dto.trainer;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainerActivateRequestDTO {
    @NotNull
    private String username;
    @NotNull
    private Boolean isActive;
}
