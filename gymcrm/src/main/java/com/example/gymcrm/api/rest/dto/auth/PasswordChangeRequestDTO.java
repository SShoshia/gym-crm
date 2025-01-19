package com.example.gymcrm.api.rest.dto.auth;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PasswordChangeRequestDTO {
    @NotNull
    private String username;
    @NotNull
    private String oldPassword;
    @NotNull
    private String newPassword;
}
