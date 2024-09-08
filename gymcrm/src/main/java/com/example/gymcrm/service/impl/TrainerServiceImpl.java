package com.example.gymcrm.service.impl;

import com.example.gymcrm.dao.core.TrainerDAO;
import com.example.gymcrm.model.Trainer;
import com.example.gymcrm.service.core.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainerServiceImpl implements TrainerService {

    private final TrainerDAO trainerDAO;

    @Autowired
    public TrainerServiceImpl(TrainerDAO trainerDAO) {
        this.trainerDAO = trainerDAO;
    }

    @Override
    public void createTrainer(Trainer trainer) {
        trainerDAO.create(trainer);
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
        trainerDAO.update(trainer);
    }

    @Override
    public void deleteTrainer(Long id) {
        trainerDAO.delete(id);
    }
}
