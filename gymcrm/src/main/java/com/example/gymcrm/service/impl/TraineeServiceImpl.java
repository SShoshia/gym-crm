package com.example.gymcrm.service.impl;

import com.example.gymcrm.dao.core.TraineeDAO;
import com.example.gymcrm.model.Trainee;
import com.example.gymcrm.service.core.TraineeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TraineeServiceImpl implements TraineeService {

    private final TraineeDAO traineeDAO;

    @Autowired
    public TraineeServiceImpl(TraineeDAO traineeDAO) {
        this.traineeDAO = traineeDAO;
    }

    @Override
    public void createTrainee(Trainee trainee) {
        traineeDAO.create(trainee);
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
}
