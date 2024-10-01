package com.example.gymcrm.config;


import com.example.gymcrm.auth.Authenticator;
import com.example.gymcrm.auth.AuthenticatorImpl;
import com.example.gymcrm.dao.core.UserDAO;
import com.example.gymcrm.datainitialization.DataInitializationConfig;
import com.example.gymcrm.datainitialization.DataInitializer;
import com.example.gymcrm.facade.GymCRMFacade;
import com.example.gymcrm.facade.GymCRMFacadeImpl;
import com.example.gymcrm.fileservice.FileService;
import com.example.gymcrm.model.entity.Trainee;
import com.example.gymcrm.model.entity.Trainer;
import com.example.gymcrm.model.entity.Training;
import com.example.gymcrm.model.entity.User;
import com.example.gymcrm.service.core.TraineeService;
import com.example.gymcrm.service.core.TrainerService;
import com.example.gymcrm.service.core.TrainingService;
import com.example.gymcrm.service.core.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

import java.util.Map;

@Configuration
@PropertySource("classpath:application.properties")
@Import({StorageConfig.class, DAOConfig.class, ServiceConfig.class, HibernateConfig.class})
public class AppConfig {

    @Bean
    public GymCRMFacade gymCRMFacade(UserService userService, TraineeService traineeService, TrainerService trainerService, TrainingService trainingService) {
        return new GymCRMFacadeImpl(userService, traineeService, trainerService, trainingService);
    }

    @Bean
    public FileService fileService() {
        return new FileService();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public DataInitializer dataInitializer(FileService fileService, ObjectMapper objectMapper) {
        return new DataInitializer(fileService, objectMapper);
    }

    @Bean
    public DataInitializationConfig dataInitializationConfig(Map<Long, User> userStorage, Map<Long, Trainee> traineeStorage, Map<Long, Trainer> trainerStorage, Map<Long, Training> trainingStorage, DataInitializer dataInitializer) {
        return new DataInitializationConfig(userStorage, traineeStorage, trainerStorage, trainingStorage, dataInitializer);
    }

    @Bean
    public Authenticator authenticator(UserDAO userDAO) {
        return new AuthenticatorImpl(userDAO);
    }

}
