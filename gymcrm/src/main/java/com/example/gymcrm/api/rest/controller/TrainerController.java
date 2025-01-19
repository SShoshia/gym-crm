package com.example.gymcrm.api.rest.controller;

import com.example.gymcrm.api.rest.dto.trainer.*;
import com.example.gymcrm.api.rest.error.exception.ActivateTrainerException;
import com.example.gymcrm.api.rest.error.exception.TrainerRegistrationException;
import com.example.gymcrm.api.rest.error.exception.TrainerUpdateException;
import com.example.gymcrm.model.entity.Trainer;
import com.example.gymcrm.model.entity.User;
import com.example.gymcrm.service.core.TrainerService;
import com.example.gymcrm.service.core.UserService;
import jakarta.validation.Valid;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import static com.example.gymcrm.util.GeneralUtil.defaultIfNull;

@RestController
@RequestMapping("/api/trainers")
public class TrainerController {

    private final UserService userService;
    private final TrainerService trainerService;

    @Autowired
    public TrainerController(UserService userService, TrainerService trainerService) {
        this.userService = userService;
        this.trainerService = trainerService;
    }

    @PostMapping("/register")
    public ResponseEntity<TrainerRegistrationResponseDTO> registerTrainer(@RequestBody TrainerRegistrationRequestDTO requestDTO) {
        val user = userService.createUser(User.builder().firstName(requestDTO.getFirstName()).lastName(requestDTO.getLastName()).isActive(true).build());
        try {
            trainerService.createTrainer(Trainer.builder().user(user).specialization(requestDTO.getSpecialization()).build());
        } catch (Exception e) {
            userService.deleteUser(user.getId());
            throw new TrainerRegistrationException("Error while registering trainer");
        }
        val responseDTO = new TrainerRegistrationResponseDTO(user.getUsername(), user.getPassword());
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/profile")
    public ResponseEntity<TrainerProfileDTO> getTrainerProfile(@RequestParam String username) {
        val trainer = trainerService.getTrainerByUsername(username);
        if (trainer.isEmpty()) {
            throw new TrainerRegistrationException("Trainer " + username + " not found");
        }

        val foundTrainer = trainer.get();
        val profileDTO = new TrainerProfileDTO(
                foundTrainer.getUsername(),
                foundTrainer.getUser().getFirstName(),
                foundTrainer.getUser().getLastName(),
                foundTrainer.getSpecialization(),
                foundTrainer.getUser().getIsActive(),
                new ArrayList<>()
        );
        return ResponseEntity.ok(profileDTO);
    }

    @PutMapping("/profile")
    public ResponseEntity<TrainerProfileDTO> updateTrainerProfile(@Valid @RequestBody TrainerUpdateProfileDTO requestDTO) {
        val trainer = trainerService.getTrainerByUsername(requestDTO.getUsername());
        if (trainer.isEmpty()) {
            throw new TrainerRegistrationException("Trainer " + requestDTO.getUsername() + " not found");
        }

        val foundTrainer = trainer.get();
        val foundUser = foundTrainer.getUser();
        foundUser.setFirstName(requestDTO.getFirstName());
        foundUser.setLastName(requestDTO.getLastName());
        foundUser.setIsActive(requestDTO.getIsActive());
        try {
            val updatedTrainer = trainerService.updateTrainer(Trainer.builder()
                    .user(foundUser)
                    .specialization(defaultIfNull(requestDTO.getSpecialization(), foundTrainer.getSpecialization()))
                    .build());
            val responseTrainerDTO = new TrainerProfileDTO(
                    updatedTrainer.getUsername(),
                    updatedTrainer.getUser().getFirstName(),
                    updatedTrainer.getUser().getLastName(),
                    updatedTrainer.getSpecialization(),
                    updatedTrainer.getUser().getIsActive(),
                    new ArrayList<>()
            );
            return ResponseEntity.ok(responseTrainerDTO);
        } catch (Exception e) {
            throw new TrainerUpdateException("Error while updating trainer " + requestDTO.getUsername());
        }
    }

    @PatchMapping("/activate")
    public ResponseEntity<Void> activateTrainee(@Valid @RequestBody TrainerActivateRequestDTO requestDTO) {
        val user = userService.getUserByUsername(requestDTO.getUsername());
        if (user.isEmpty()) {
            throw new ActivateTrainerException("Error while activating trainee " + requestDTO.getUsername());
        }

        val foundUser = user.get();
        foundUser.setIsActive(requestDTO.getIsActive());
        userService.updateUser(foundUser);

        return ResponseEntity.ok().build();
    }

}
