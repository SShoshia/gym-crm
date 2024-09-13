package com.example.gymcrm.service.impl;

import com.example.gymcrm.dao.core.TraineeDAO;
import com.example.gymcrm.dao.core.TrainerDAO;
import com.example.gymcrm.dao.core.TrainingDAO;
import com.example.gymcrm.model.Training;
import com.example.gymcrm.service.core.TrainingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainingServiceImpl implements TrainingService {

    private static final Logger logger = LoggerFactory.getLogger(TrainingServiceImpl.class);

    private final TrainingDAO trainingDAO;
    private final TraineeDAO traineeDAO;
    private final TrainerDAO trainerDAO;

    @Autowired
    public TrainingServiceImpl(TrainingDAO trainingDAO, TraineeDAO traineeDAO, TrainerDAO trainerDAO) {
        this.trainingDAO = trainingDAO;
        this.traineeDAO = traineeDAO;
        this.trainerDAO = trainerDAO;
    }

    @Override
    public void createTraining(Training training) {
        boolean traineePresent = traineeDAO.findById(training.getTraineeId()).isPresent(), trainerPresent = trainerDAO.findById(training.getTrainerId()).isPresent();
        if (traineePresent && trainerPresent) {
            trainingDAO.create(training);
        } else {
            if (!traineePresent) {
                error("Trainee with id " + training.getTraineeId() + " not found. Training: " + training);
            }
            if (!trainerPresent) {
                error("Trainer with id " + training.getTrainerId() + " not found. Training: " + training);
            }
        }
    }

    @Override
    public Optional<Training> getTraining(Long id) {
        return trainingDAO.findById(id);
    }

    @Override
    public List<Training> getAllTrainings() {
        return trainingDAO.findAll();
    }

    private void error(String errorMessage) {
        logger.error(errorMessage);
        throw new RuntimeException(errorMessage);
    }

}
