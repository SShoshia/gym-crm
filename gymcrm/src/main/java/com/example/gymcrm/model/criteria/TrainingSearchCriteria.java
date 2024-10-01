package com.example.gymcrm.model.criteria;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TrainingSearchCriteria {
    String traineeUsername;
    String trainerUsername;
    Date dateFrom;
    Date dateTo;
    String trainingType;
}
