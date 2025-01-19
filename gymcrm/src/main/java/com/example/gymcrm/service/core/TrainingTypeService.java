package com.example.gymcrm.service.core;

import com.example.gymcrm.model.entity.TrainingType;

import java.util.List;
import java.util.Optional;

public interface TrainingTypeService {

    void createTrainingType(TrainingType trainingType);

    List<TrainingType> getAllTrainings();

    Optional<TrainingType> getTrainingType(Long id);

    Optional<TrainingType> getTrainingTypeByName(String name);

}
