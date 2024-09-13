package com.example.gymcrm.config;


import com.example.gymcrm.datainitialization.DataInitializationConfig;
import com.example.gymcrm.datainitialization.DataInitializer;
import com.example.gymcrm.facade.GymCRMFacade;
import com.example.gymcrm.facade.GymCRMFacadeImpl;
import com.example.gymcrm.fileservice.FileService;
import com.example.gymcrm.model.Trainee;
import com.example.gymcrm.model.Trainer;
import com.example.gymcrm.model.Training;
import com.example.gymcrm.model.User;
import com.example.gymcrm.service.core.TrainerService;
import com.example.gymcrm.service.core.TraineeService;
import com.example.gymcrm.service.core.TrainingService;
import com.example.gymcrm.service.core.UserService;
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
    public DataInitializer dataInitializer(FileService fileService) {
        return new DataInitializer(fileService);
    }

    @Bean
    public DataInitializationConfig dataInitializationConfig(Map<Long, User> userStorage, Map<Long, Trainee> traineeStorage, Map<Long, Trainer> trainerStorage, Map<Long, Training> trainingStorage, DataInitializer dataInitializer) {
        return new DataInitializationConfig(userStorage, traineeStorage, trainerStorage, trainingStorage, dataInitializer);
    }

}
