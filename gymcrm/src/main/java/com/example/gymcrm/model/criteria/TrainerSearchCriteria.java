package com.example.gymcrm.model.criteria;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TrainerSearchCriteria {
    String traineeUsernameNotAssigned;
    Boolean isActive;
}
