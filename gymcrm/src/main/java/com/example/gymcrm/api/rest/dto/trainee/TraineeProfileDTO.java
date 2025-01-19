package com.example.gymcrm.api.rest.dto.trainee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TraineeProfileDTO {
    private String username;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String address;
    private Boolean isActive;
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
