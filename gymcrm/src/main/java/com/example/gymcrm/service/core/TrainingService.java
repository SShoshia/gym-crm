package com.example.gymcrm.service.core;

import com.example.gymcrm.model.Training;

import java.util.List;
import java.util.Optional;

public interface TrainingService {

    void createTraining(Training training);

    Optional<Training> getTraining(Long id);

    List<Training> getAllTrainings();

}
