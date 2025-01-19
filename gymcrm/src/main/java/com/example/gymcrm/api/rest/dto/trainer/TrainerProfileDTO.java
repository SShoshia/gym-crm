package com.example.gymcrm.api.rest.dto.trainer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainerProfileDTO {
    private String username;
    private String firstName;
    private String lastName;
    private String specialization;
    private Boolean isActive;
    private List<TraineeInfo> trainees;

    @Data
    static class TraineeInfo {
        private String username;
        private String firstName;
        private String lastName;
    }
}
