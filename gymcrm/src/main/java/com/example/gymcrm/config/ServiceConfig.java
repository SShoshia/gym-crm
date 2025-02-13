package com.example.gymcrm.config;

import com.example.gymcrm.auth.Authenticator;
import com.example.gymcrm.dao.core.TraineeDAO;
import com.example.gymcrm.dao.core.TrainerDAO;
import com.example.gymcrm.dao.core.TrainingDAO;
import com.example.gymcrm.dao.core.UserDAO;
import com.example.gymcrm.service.core.*;
import com.example.gymcrm.service.impl.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    public UserService userService(UserDAO userDAO) {
        return new UserServiceImpl(userDAO);
    }

    @Bean
    public TraineeService traineeService(TraineeDAO traineeDAO, UserDAO userDAO, Authenticator authenticator) {
        return new TraineeServiceImpl(traineeDAO, userDAO, authenticator);
    }

    @Bean
    public TrainerService trainerService(TrainerDAO trainerDAO, UserDAO userDAO, Authenticator authenticator) {
        return new TrainerServiceImpl(trainerDAO, userDAO, authenticator);
    }

    @Bean
    public TrainingService trainingService(TrainingDAO trainingDAO, TraineeDAO traineeDAO, TrainerDAO trainerDAO) {
        return new TrainingServiceImpl(trainingDAO, traineeDAO, trainerDAO);
    }

    @Bean
    public TrainingTypeService trainingTypeService() {
        return new TrainingTypeServiceImpl();
    }

}
