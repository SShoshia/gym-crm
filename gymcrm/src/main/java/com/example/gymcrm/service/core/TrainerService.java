package com.example.gymcrm.service.core;

import com.example.gymcrm.model.entity.Trainer;
import com.example.gymcrm.model.criteria.TrainerSearchCriteria;

import java.util.List;
import java.util.Optional;

public interface TrainerService {

    void createTrainer(Trainer trainer) throws Exception;

    Optional<Trainer> getTrainer(Long id);

    List<Trainer> getAllTrainers();

    void updateTrainer(Trainer trainer) throws Exception;

    void deleteTrainer(Trainer trainer) throws Exception;

    List<Trainer> getTrainersMatchingCriteria(TrainerSearchCriteria criteria);

    Optional<Trainer> getTrainerByUsername(String username);

}
