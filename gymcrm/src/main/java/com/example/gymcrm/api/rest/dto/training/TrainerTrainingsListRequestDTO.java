package com.example.gymcrm.api.rest.dto.training;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainerTrainingsListRequestDTO {
    @NotNull
    private String trainerUsername;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private String traineeUsername;
}
