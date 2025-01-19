package com.example.gymcrm.dao.core;

import com.example.gymcrm.model.entity.Trainee;

import java.util.List;
import java.util.Optional;

public interface TraineeDAO {

    void create(Trainee trainee);

    Optional<Trainee> findById(Long id);

    List<Trainee> findAll();

    Trainee update(Trainee trainee);

    void delete(Long id);

}
