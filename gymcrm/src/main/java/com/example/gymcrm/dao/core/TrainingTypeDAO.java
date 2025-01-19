package com.example.gymcrm.dao.core;

import com.example.gymcrm.model.entity.TrainingType;

import java.util.List;
import java.util.Optional;

public interface TrainingTypeDAO {

    void create(TrainingType trainingType);

    Optional<TrainingType> findById(Long id);

    Optional<TrainingType> findByName(String name);

    List<TrainingType> findAll();

    void delete(Long id);

}
