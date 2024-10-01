package com.example.gymcrm.facade;

import com.example.gymcrm.model.entity.Trainee;
import com.example.gymcrm.model.entity.Trainer;
import com.example.gymcrm.model.entity.Training;
import com.example.gymcrm.model.entity.User;
import com.example.gymcrm.model.criteria.TrainerSearchCriteria;
import com.example.gymcrm.model.criteria.TrainingSearchCriteria;

import java.util.List;
import java.util.Optional;

public interface GymCRMFacade {

    void createUser(User user);

    Optional<User> getUser(Long id);

    List<User> getAllUsers();

    void updateUser(User user);

    void deleteUser(Long id);


    void createTrainee(Trainee trainee) throws Exception;

    Optional<Trainee> getTrainee(Long id);

    List<Trainee> getAllTrainees();

    void updateTrainee(Trainee trainee) throws Exception;

    void deleteTrainee(Trainee trainee) throws Exception;


    void createTrainer(Trainer trainer) throws Exception;

    Optional<Trainer> getTrainer(Long id);

    List<Trainer> getAllTrainers();

    void updateTrainer(Trainer trainer) throws Exception;

    void deleteTrainer(Trainer trainer) throws Exception;


    void createTraining(Training training);

    Optional<Training> getTraining(Long id);

    List<Training> getAllTrainings();

    List<Training> getTrainingsMatchingCriteria(TrainingSearchCriteria criteria);

    List<Trainer> getTrainersMatchingCriteria(TrainerSearchCriteria criteria);

}
