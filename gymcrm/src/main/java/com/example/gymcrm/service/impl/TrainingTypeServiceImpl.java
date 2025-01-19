package com.example.gymcrm.service.impl;

import com.example.gymcrm.model.entity.TrainingType;
import com.example.gymcrm.service.core.TrainingTypeService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class TrainingTypeServiceImpl implements TrainingTypeService {

    private static final Logger logger = LoggerFactory.getLogger(TrainingTypeServiceImpl.class);

    @Autowired
    public TrainingTypeServiceImpl() {

    }

    @Override
    public void createTrainingType(TrainingType trainingType) {

    }

    @Override
    public List<TrainingType> getAllTrainings() {
        return List.of();
    }

    @Override
    public Optional<TrainingType> getTrainingType(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<TrainingType> getTrainingTypeByName(String name) {
        return Optional.empty();
    }
}
