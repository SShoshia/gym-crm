package com.example.gymcrm.service.core;

import com.example.gymcrm.model.Trainer;

import java.util.List;
import java.util.Optional;

public interface TrainerService {

    void createTrainer(Trainer trainer);

    Optional<Trainer> getTrainer(Long id);

    List<Trainer> getAllTrainers();

    void updateTrainer(Trainer trainer);

    void deleteTrainer(Long id);

}
