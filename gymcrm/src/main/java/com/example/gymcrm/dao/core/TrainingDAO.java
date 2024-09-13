package com.example.gymcrm.dao.core;

import com.example.gymcrm.model.Training;

import java.util.List;
import java.util.Optional;

public interface TrainingDAO {

    void create(Training training);

    Optional<Training> findById(Long id);

    List<Training> findAll();

    void delete(Long id);

}
