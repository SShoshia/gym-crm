package com.example.gymcrm.facade;

import com.example.gymcrm.model.Trainee;
import com.example.gymcrm.model.Trainer;
import com.example.gymcrm.model.Training;
import com.example.gymcrm.model.User;

import java.util.List;
import java.util.Optional;

public interface GymCRMFacade {

    void createUser(User user);

    Optional<User> getUser(Long id);

    List<User> getAllUsers();

    void updateUser(User user);

    void deleteUser(Long id);


    void createTrainee(Trainee trainee);

    Optional<Trainee> getTrainee(Long id);

    List<Trainee> getAllTrainees();

    void updateTrainee(Trainee trainee);

    void deleteTrainee(Long id);


    void createTrainer(Trainer trainer);

    Optional<Trainer> getTrainer(Long id);

    List<Trainer> getAllTrainers();

    void updateTrainer(Trainer trainer);

    void deleteTrainer(Long id);


    void createTraining(Training training);

    Optional<Training> getTraining(Long id);

    List<Training> getAllTrainings();

}
