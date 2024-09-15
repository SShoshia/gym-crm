package com.example.gymcrm.config;

import com.example.gymcrm.dao.core.TraineeDAO;
import com.example.gymcrm.dao.core.TrainerDAO;
import com.example.gymcrm.dao.core.TrainingDAO;
import com.example.gymcrm.dao.core.UserDAO;
import com.example.gymcrm.dao.inmemory.InMemoryTraineeDAO;
import com.example.gymcrm.dao.inmemory.InMemoryTrainerDAO;
import com.example.gymcrm.dao.inmemory.InMemoryTrainingDAO;
import com.example.gymcrm.dao.inmemory.InMemoryUserDAO;
import com.example.gymcrm.model.Trainee;
import com.example.gymcrm.model.Trainer;
import com.example.gymcrm.model.Training;
import com.example.gymcrm.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class DAOConfig {

    @Bean
    public UserDAO userDAO(Map<Long, User> userStorage) {
        return new InMemoryUserDAO(userStorage);
    }

    @Bean
    public TraineeDAO traineeDAO(Map<Long, Trainee> traineeStorage) {
        return new InMemoryTraineeDAO(traineeStorage);
    }

    @Bean
    public TrainerDAO trainerDAO(Map<Long, Trainer> trainerStorage) {
        return new InMemoryTrainerDAO(trainerStorage);
    }

    @Bean
    public TrainingDAO trainingDAO(Map<Long, Training> trainingStorage) {
        return new InMemoryTrainingDAO(trainingStorage);
    }

}
