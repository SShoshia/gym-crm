package com.example.gymcrm.service.impl;

import com.example.gymcrm.dao.core.TrainingDAO;
import com.example.gymcrm.model.Training;
import com.example.gymcrm.service.core.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainingServiceImpl implements TrainingService {

    private final TrainingDAO trainingDAO;

    @Autowired
    public TrainingServiceImpl(TrainingDAO trainingDAO) {
        this.trainingDAO = trainingDAO;
    }

    @Override
    public void createTraining(Training training) {
        trainingDAO.create(training);
    }

    @Override
    public Optional<Training> getTraining(Long id) {
        return trainingDAO.findById(id);
    }

    @Override
    public List<Training> getAllTrainings() {
        return trainingDAO.findAll();
    }

}
