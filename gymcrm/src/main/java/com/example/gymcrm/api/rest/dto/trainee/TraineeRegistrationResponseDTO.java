package com.example.gymcrm.api.rest.dto.trainee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TraineeRegistrationResponseDTO {
    private String username;
    private String password;
}
