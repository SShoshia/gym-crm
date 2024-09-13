package com.example.gymcrm.config;

import com.example.gymcrm.dao.core.TraineeDAO;
import com.example.gymcrm.dao.core.TrainerDAO;
import com.example.gymcrm.dao.core.TrainingDAO;
import com.example.gymcrm.dao.core.UserDAO;
import com.example.gymcrm.service.core.TrainerService;
import com.example.gymcrm.service.core.TraineeService;
import com.example.gymcrm.service.core.TrainingService;
import com.example.gymcrm.service.core.UserService;
import com.example.gymcrm.service.impl.TraineeServiceImpl;
import com.example.gymcrm.service.impl.TrainerServiceImpl;
import com.example.gymcrm.service.impl.TrainingServiceImpl;
import com.example.gymcrm.service.impl.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    public UserService userService(UserDAO userDAO) {
        return new UserServiceImpl(userDAO);
    }

    @Bean
    public TraineeService traineeService(TraineeDAO traineeDAO) {
        return new TraineeServiceImpl(traineeDAO);
    }

    @Bean
    public TrainerService trainerService(TrainerDAO trainerDAO) {
        return new TrainerServiceImpl(trainerDAO);
    }

    @Bean
    public TrainingService trainingService(TrainingDAO trainingDAO) {
        return new TrainingServiceImpl(trainingDAO);
    }

}
