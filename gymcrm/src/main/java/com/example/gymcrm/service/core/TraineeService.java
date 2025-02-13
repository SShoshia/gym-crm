package com.example.gymcrm.service.core;

import com.example.gymcrm.model.entity.Trainee;

import java.util.List;
import java.util.Optional;

public interface TraineeService {

    void createTrainee(Trainee trainee) throws Exception;

    Optional<Trainee> getTrainee(Long id);

    List<Trainee> getAllTrainees();

    Trainee updateTrainee(Trainee trainee) throws Exception;

    void deleteTrainee(Trainee trainee) throws Exception;

    Optional<Trainee> getTraineeByUsername(String username);

    void deleteTraineeByUsername(String username);
}
