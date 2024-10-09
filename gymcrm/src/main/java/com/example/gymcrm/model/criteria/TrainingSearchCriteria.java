package com.example.gymcrm.model.criteria;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TrainingSearchCriteria {
    String traineeUsername;
    String trainerUsername;
    LocalDate dateFrom;
    LocalDate dateTo;
    String trainingType;
}
