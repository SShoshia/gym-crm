package com.example.gymcrm.config;

import com.example.gymcrm.dao.core.*;
import com.example.gymcrm.dao.hibernate.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DAOConfig {

    @Bean
    public UserDAO userDAO() {
        return new HibernateUserDAO();
    }

    @Bean
    public TraineeDAO traineeDAO() {
        return new HibernateTraineeDAO();
    }

    @Bean
    public TrainerDAO trainerDAO() {
        return new HibernateTrainerDAO();
    }

    @Bean
    public TrainingDAO trainingDAO() {
        return new HibernateTrainingDAO();
    }

    @Bean
    public TrainingTypeDAO trainingTypeDAO() {
        return new HibernateTrainingTypeDAO();
    }

}
