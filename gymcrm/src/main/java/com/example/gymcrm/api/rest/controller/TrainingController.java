package com.example.gymcrm.api.rest.controller;

import com.example.gymcrm.api.rest.dto.training.*;
import com.example.gymcrm.api.rest.error.exception.TrainingAddException;
import com.example.gymcrm.model.criteria.TrainingSearchCriteria;
import com.example.gymcrm.model.entity.Training;
import com.example.gymcrm.service.core.TraineeService;
import com.example.gymcrm.service.core.TrainerService;
import com.example.gymcrm.service.core.TrainingService;
import com.example.gymcrm.service.core.UserService;
import jakarta.validation.Valid;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/trainings")
public class TrainingController {

    private final UserService userService;
    private final TraineeService traineeService;
    private final TrainerService trainerService;
    private final TrainingService trainingService;

    public TrainingController(UserService userService, TraineeService traineeService, TrainerService trainerService, TrainingService trainingService) {
        this.userService = userService;
        this.traineeService = traineeService;
        this.trainerService = trainerService;
        this.trainingService = trainingService;
    }

    @GetMapping("/trainee-trainings")
    public ResponseEntity<TraineeTrainingsListResponseDTO> getTraineeTrainings(@Valid @RequestParam TraineeTrainingsListRequestDTO requestDTO) {
        val trainings = trainingService.getTrainingsMatchingCriteria(new TrainingSearchCriteria(
                requestDTO.getTraineeUsername(),
                requestDTO.getTrainerUsername(),
                requestDTO.getDateFrom(),
                requestDTO.getDateTo(),
                requestDTO.getTrainingType()
        ));
        val res = new TraineeTrainingsListResponseDTO(trainings.stream().map(training ->
                new TraineeTrainingsListResponseDTO.TrainingInfo(
                        training.getTrainingName(),
                        training.getTrainingDate(),
                        training.getTrainingType(),
                        training.getTrainingDuration(),
                        training.getTrainerUsername()
                )
        ).toList());

        return ResponseEntity.ok(res);
    }

    @GetMapping("/trainer-trainings")
    public ResponseEntity<TrainerTrainingsListResponseDTO> getTrainerTrainings(@Valid @RequestParam TrainerTrainingsListRequestDTO requestDTO) {
        val trainings = trainingService.getTrainingsMatchingCriteria(new TrainingSearchCriteria(
                requestDTO.getTraineeUsername(),
                requestDTO.getTrainerUsername(),
                requestDTO.getDateFrom(),
                requestDTO.getDateTo(),
                null
        ));
        val res = new TrainerTrainingsListResponseDTO(trainings.stream().map(training ->
                new TrainerTrainingsListResponseDTO.TrainingInfo(
                        training.getTrainingName(),
                        training.getTrainingDate(),
                        training.getTrainingType(),
                        training.getTrainingDuration(),
                        training.getTrainerUsername()
                )
        ).toList());

        return ResponseEntity.ok(res);
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addTraining(@Valid @RequestBody AddTrainingRequestDTO requestDTO) {
        val trainee = traineeService.getTraineeByUsername(requestDTO.getTraineeUsername());
        val trainer = trainerService.getTrainerByUsername(requestDTO.getTrainerUsername());
        if (trainee.isEmpty() || trainer.isEmpty()) {
            throw new TrainingAddException("Error while adding training " + requestDTO.getTrainingName());
        }

        trainingService.createTraining(
                Training.builder()
                        .trainee(trainee.get())
                        .trainer(trainer.get())
                        .trainingName(requestDTO.getTrainingName())
                        .trainingType(requestDTO.getTrainingType())
                        .trainingDate(requestDTO.getTrainingDate())
                        .trainingDuration(requestDTO.getTrainingDuration())
                        .build()
        );

        return ResponseEntity.ok().build();
    }

    @GetMapping("training-types")
    public ResponseEntity<TrainingTypesResponseDTO> getTrainingTypes() {
        return ResponseEntity.ok(
                new TrainingTypesResponseDTO(
                        trainingService.getAllTrainings()
                                .stream()
                                .map(Training::getTrainingType)
                                .distinct()
                                .map(TrainingTypesResponseDTO.TrainingTypeInfo::new)
                                .toList()
                )
        );
    }

}
