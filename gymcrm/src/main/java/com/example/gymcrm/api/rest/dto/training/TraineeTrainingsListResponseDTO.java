package com.example.gymcrm.api.rest.dto.training;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TraineeTrainingsListResponseDTO {
    private List<TrainingInfo> trainings;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TrainingInfo {
        private String name;
        private LocalDate date;
        private String type;
        private Integer duration;
        private String trainerName;
    }
}
