package com.example.gymcrm.dao.core;

import com.example.gymcrm.model.entity.Trainer;

import java.util.List;
import java.util.Optional;

public interface TrainerDAO {

    void create(Trainer trainer);

    Optional<Trainer> findById(Long id);

    List<Trainer> findAll();

    Trainer update(Trainer trainer);

    void delete(Long id);

}
