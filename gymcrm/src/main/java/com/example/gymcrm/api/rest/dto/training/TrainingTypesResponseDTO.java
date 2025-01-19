package com.example.gymcrm.api.rest.dto.training;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainingTypesResponseDTO {
    private List<TrainingTypeInfo> trainingTypes;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TrainingTypeInfo {
        String trainingType;
    }
}
