package com.example.gymcrm.api.rest.dto.trainer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainerRegistrationResponseDTO {
    private String username;
    private String password;
}
