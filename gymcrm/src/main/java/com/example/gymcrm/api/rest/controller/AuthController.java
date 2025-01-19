package com.example.gymcrm.api.rest.controller;

import com.example.gymcrm.api.rest.dto.auth.LoginRequestDTO;
import com.example.gymcrm.api.rest.dto.auth.PasswordChangeRequestDTO;
import com.example.gymcrm.api.rest.error.exception.InvalidCredentialsException;
import com.example.gymcrm.auth.Authenticator;
import com.example.gymcrm.service.core.UserService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final Authenticator authenticator;

    @Autowired
    public AuthController(UserService userService, Authenticator authenticator) {
        this.userService = userService;
        this.authenticator = authenticator;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(LoginRequestDTO requestDTO) {
        val user = userService.getUserByUsername(requestDTO.getUsername());
        if (user.isPresent() && user.get().getPassword().equals(requestDTO.getPassword())) {
            return ResponseEntity.ok().build();
        }
        throw new InvalidCredentialsException("Username or password incorrect");
    }

    @PostMapping("/changeLogin")
    public ResponseEntity<?> changeLogin(PasswordChangeRequestDTO requestDTO) {
        val user = userService.getUserByUsername(requestDTO.getUsername());
        if (user.isPresent() && user.get().getPassword().equals(requestDTO.getOldPassword())) {
            val updatedUser = user.get();
            updatedUser.setPassword(requestDTO.getNewPassword());
            userService.updateUser(user.get());
            return ResponseEntity.ok().build();
        }
        throw new InvalidCredentialsException("Username or password incorrect");
    }

}
