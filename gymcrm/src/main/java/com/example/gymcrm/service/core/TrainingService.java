package com.example.gymcrm.service.core;

import com.example.gymcrm.model.entity.Training;
import com.example.gymcrm.model.criteria.TrainingSearchCriteria;

import java.util.List;
import java.util.Optional;

public interface TrainingService {

    void createTraining(Training training);

    Optional<Training> getTraining(Long id);

    List<Training> getAllTrainings();

    List<Training> getTrainingsMatchingCriteria(TrainingSearchCriteria criteria);


}
