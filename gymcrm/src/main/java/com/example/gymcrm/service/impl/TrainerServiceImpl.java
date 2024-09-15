package com.example.gymcrm.service.impl;

import com.example.gymcrm.dao.core.TrainerDAO;
import com.example.gymcrm.dao.core.UserDAO;
import com.example.gymcrm.model.Trainer;
import com.example.gymcrm.service.core.TrainerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainerServiceImpl implements TrainerService {

    private static final Logger logger = LoggerFactory.getLogger(TrainerServiceImpl.class);

    private final TrainerDAO trainerDAO;
    private final UserDAO userDAO;

    @Autowired
    public TrainerServiceImpl(TrainerDAO trainerDAO, UserDAO userDAO) {
        this.trainerDAO = trainerDAO;
        this.userDAO = userDAO;
    }

    @Override
    public void createTrainer(Trainer trainer) {
        if (userDAO.findById(trainer.getUserId()).isPresent()) {
            trainerDAO.create(trainer);
        } else {
            error("User with provided userId not found. Trainer: " + trainer);
        }
    }

    @Override
    public Optional<Trainer> getTrainer(Long id) {
        return trainerDAO.findById(id);
    }

    @Override
    public List<Trainer> getAllTrainers() {
        return trainerDAO.findAll();
    }

    @Override
    public void updateTrainer(Trainer trainer) {
        if (trainer.getId() != null && trainerDAO.findById(trainer.getId()).isPresent() &&
                trainer.getUserId() != null && userDAO.findById(trainer.getUserId()).isPresent()) {
            trainerDAO.update(trainer);
        } else {
            error("Error while updating trainer. Trainer: " + trainer);
        }
    }

    @Override
    public void deleteTrainer(Long id) {
        trainerDAO.delete(id);
    }

    private void error(String errorMessage) {
        logger.error(errorMessage);
        throw new RuntimeException(errorMessage);
    }
}
