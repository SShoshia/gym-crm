package com.example.gymcrm.facade;

import com.example.gymcrm.model.Trainee;
import com.example.gymcrm.model.Trainer;
import com.example.gymcrm.model.Training;
import com.example.gymcrm.model.User;
import com.example.gymcrm.service.core.TrainerService;
import com.example.gymcrm.service.core.TraineeService;
import com.example.gymcrm.service.core.TrainingService;
import com.example.gymcrm.service.core.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class GymCRMFacadeImpl implements GymCRMFacade {

    private final UserService userService;
    private final TraineeService traineeService;
    private final TrainerService trainerService;
    private final TrainingService trainingService;

    @Autowired
    public GymCRMFacadeImpl(UserService userService, TraineeService traineeService, TrainerService trainerService, TrainingService trainingService) {
        this.userService = userService;
        this.traineeService = traineeService;
        this.trainerService = trainerService;
        this.trainingService = trainingService;
    }


    @Override
    public void createUser(User user) {
        userService.createUser(user);
    }

    @Override
    public Optional<User> getUser(Long id) {
        return userService.getUser(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @Override
    public void updateUser(User user) {
        userService.updateUser(user);
    }

    @Override
    public void deleteUser(Long id) {
        userService.deleteUser(id);
    }


    @Override
    public void createTrainee(Trainee trainee) {
        traineeService.createTrainee(trainee);
    }

    @Override
    public Optional<Trainee> getTrainee(Long id) {
        return traineeService.getTrainee(id);
    }

    @Override
    public List<Trainee> getAllTrainees() {
        return traineeService.getAllTrainees();
    }

    @Override
    public void updateTrainee(Trainee trainee) {
        traineeService.updateTrainee(trainee);
    }

    @Override
    public void deleteTrainee(Long id) {
        traineeService.deleteTrainee(id);
    }


    @Override
    public void createTrainer(Trainer trainer) {
        trainerService.createTrainer(trainer);
    }

    @Override
    public Optional<Trainer> getTrainer(Long id) {
        return trainerService.getTrainer(id);
    }

    @Override
    public List<Trainer> getAllTrainers() {
        return trainerService.getAllTrainers();
    }

    @Override
    public void updateTrainer(Trainer trainer) {
        trainerService.updateTrainer(trainer);
    }

    @Override
    public void deleteTrainer(Long id) {
        trainerService.deleteTrainer(id);
    }


    @Override
    public void createTraining(Training training) {
        trainingService.createTraining(training);
    }

    @Override
    public Optional<Training> getTraining(Long id) {
        return trainingService.getTraining(id);
    }

    @Override
    public List<Training> getAllTrainings() {
        return trainingService.getAllTrainings();
    }

}
