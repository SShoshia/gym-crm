package com.example.gymcrm.service.impl;

import com.example.gymcrm.dao.core.TraineeDAO;
import com.example.gymcrm.dao.core.UserDAO;
import com.example.gymcrm.model.Trainee;
import com.example.gymcrm.service.core.TraineeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TraineeServiceImpl implements TraineeService {

    private static final Logger logger = LoggerFactory.getLogger(TraineeServiceImpl.class);

    private final TraineeDAO traineeDAO;
    private final UserDAO userDAO;

    @Autowired
    public TraineeServiceImpl(TraineeDAO traineeDAO, UserDAO userDAO) {
        this.traineeDAO = traineeDAO;
        this.userDAO = userDAO;
    }

    @Override
    public void createTrainee(Trainee trainee) {
        if(userDAO.findById(trainee.getUserId()).isPresent()) {
            traineeDAO.create(trainee);
        } else {
            error("User with provided userId not found. Trainee: " + trainee);
        }
    }

    @Override
    public Optional<Trainee> getTrainee(Long id) {
        return traineeDAO.findById(id);
    }

    @Override
    public List<Trainee> getAllTrainees() {
        return traineeDAO.findAll();
    }

    @Override
    public void updateTrainee(Trainee trainee) {
        traineeDAO.update(trainee);
    }

    @Override
    public void deleteTrainee(Long id) {
        traineeDAO.delete(id);
    }

    private void error(String errorMessage) {
        logger.error(errorMessage);
        throw new RuntimeException(errorMessage);
    }
}
