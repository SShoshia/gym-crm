package com.example.gymcrm.api.rest.dto.trainee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TraineeUnassignedTrainersResponseDTO {
    private List<TrainerInfo> trainers;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TrainerInfo {
        private String username;
        private String firstName;
        private String lastName;
        private String specialization;
    }

}
