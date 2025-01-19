package com.example.gymcrm.api.rest.controller;

import com.example.gymcrm.api.rest.dto.trainee.*;
import com.example.gymcrm.api.rest.error.exception.*;
import com.example.gymcrm.model.criteria.TrainerSearchCriteria;
import com.example.gymcrm.model.entity.Trainee;
import com.example.gymcrm.model.entity.User;
import com.example.gymcrm.service.core.TraineeService;
import com.example.gymcrm.service.core.TrainerService;
import com.example.gymcrm.service.core.TrainingService;
import com.example.gymcrm.service.core.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;

import static com.example.gymcrm.util.GeneralUtil.defaultIfNull;

@RestController
@RequestMapping("/api/trainee")
public class TraineeController {

    private final UserService userService;
    private final TraineeService traineeService;
    private final TrainerService trainerService;
    private final TrainingService trainingService;

    @Autowired
    public TraineeController(UserService userService, TraineeService traineeService, TrainerService trainerService, TrainingService trainingService) {
        this.userService = userService;
        this.traineeService = traineeService;
        this.trainerService = trainerService;
        this.trainingService = trainingService;
    }

    @PostMapping("/register")
    public ResponseEntity<TraineeRegistrationResponseDTO> registerTrainee(@Valid @RequestBody TraineeRegistrationRequestDTO requestDTO) {
        val user = userService.createUser(User.builder().firstName(requestDTO.getFirstName()).lastName(requestDTO.getLastName()).isActive(true).build());
        try {
            traineeService.createTrainee(Trainee.builder().user(user).dateOfBirth(LocalDate.parse(requestDTO.getDateOfBirth())).address(requestDTO.getAddress()).build());
        } catch (Exception e) {
            userService.deleteUser(user.getId());
            throw new TraineeRegistrationException("Error while registering trainee");
        }
        val responseDTO = new TraineeRegistrationResponseDTO(user.getUsername(), user.getPassword());
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/profile")
    public ResponseEntity<TraineeProfileDTO> getTraineeProfile(@RequestParam @NotNull String username) {
        val trainee = traineeService.getTraineeByUsername(username);
        if (trainee.isEmpty()) {
            throw new TraineeNotFoundException("Trainee " + username + " not found");
        }

        val foundTrainee = trainee.get();
        val profileDTO = new TraineeProfileDTO(
                foundTrainee.getUsername(),
                foundTrainee.getUser().getFirstName(),
                foundTrainee.getUser().getLastName(),
                foundTrainee.getDateOfBirth(),
                foundTrainee.getAddress(),
                foundTrainee.getUser().getIsActive(),
                new ArrayList<>()
        );
        return ResponseEntity.ok(profileDTO);
    }

    @PutMapping("/profile")
    public ResponseEntity<TraineeProfileDTO> updateTraineeProfile(@Valid @RequestBody TraineeUpdateProfileDTO requestDTO) {
        val trainee = traineeService.getTraineeByUsername(requestDTO.getUsername());
        if (trainee.isEmpty()) {
            throw new TraineeNotFoundException("Trainee " + requestDTO.getUsername() + " not found");
        }

        val foundTrainee = trainee.get();
        val foundUser = foundTrainee.getUser();
        foundUser.setFirstName(requestDTO.getFirstName());
        foundUser.setLastName(requestDTO.getLastName());
        foundUser.setIsActive(requestDTO.getIsActive());
        try {
            val updatedTrainee = traineeService.updateTrainee(Trainee.builder()
                    .user(foundUser)
                    .dateOfBirth(defaultIfNull(requestDTO.getDateOfBirth(), foundTrainee.getDateOfBirth()))
                    .address(defaultIfNull(requestDTO.getAddress(), foundTrainee.getAddress()))
                    .build());
            val responseProfileDTO = new TraineeProfileDTO(
                    updatedTrainee.getUsername(),
                    updatedTrainee.getUser().getFirstName(),
                    updatedTrainee.getUser().getLastName(),
                    updatedTrainee.getDateOfBirth(),
                    updatedTrainee.getAddress(),
                    updatedTrainee.getUser().getIsActive(),
                    new ArrayList<>()
            );
            return ResponseEntity.ok(responseProfileDTO);
        } catch (Exception e) {
            throw new TraineeUpdateException("Error while updating trainee " + requestDTO.getUsername());
        }
    }

    @DeleteMapping("/profile")
    public ResponseEntity<Void> deleteProfile(@RequestParam @NotNull String username) {
        val trainee = traineeService.getTraineeByUsername(username);
        if (trainee.isEmpty()) {
            throw new TraineeNotFoundException("Trainee " + username + " not found");
        }
        try {
            traineeService.deleteTrainee(trainee.get());
        } catch (Exception e) {
            throw new TraineeDeletionException("Error while deleting trainee " + username);
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/unassigned-trainers")
    public ResponseEntity<TraineeUnassignedTrainersResponseDTO> getUnassignedActiveTrainers(@RequestParam @NotNull String username) {
        val trainers = trainerService.getTrainersMatchingCriteria(new TrainerSearchCriteria(username, true))
                .stream()
                .map(trainer -> new TraineeUnassignedTrainersResponseDTO.TrainerInfo(
                        trainer.getUsername(),
                        trainer.getUser().getFirstName(),
                        trainer.getUser().getLastName(),
                        trainer.getSpecialization())
                ).toList();
        val res = new TraineeUnassignedTrainersResponseDTO(trainers);

        return ResponseEntity.ok(res);
    }

    @PatchMapping("/activate")
    public ResponseEntity<Void> activateTrainee(@Valid @RequestBody TraineeActivateRequestDTO requestDTO) {
        val user = userService.getUserByUsername(requestDTO.getUsername());
        if (user.isEmpty()) {
            throw new ActivateTraineeException("Error while activating trainee " + requestDTO.getUsername());
        }

        val foundUser = user.get();
        foundUser.setIsActive(requestDTO.getIsActive());
        userService.updateUser(foundUser);

        return ResponseEntity.ok().build();
    }

}
