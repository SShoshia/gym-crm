package com.example.gymcrm.config;

import com.example.gymcrm.dao.core.TraineeDAO;
import com.example.gymcrm.dao.core.TrainerDAO;
import com.example.gymcrm.dao.core.TrainingDAO;
import com.example.gymcrm.dao.core.UserDAO;
import com.example.gymcrm.dao.hibernate.HibernateTraineeDAO;
import com.example.gymcrm.dao.hibernate.HibernateTrainerDAO;
import com.example.gymcrm.dao.hibernate.HibernateTrainingDAO;
import com.example.gymcrm.dao.hibernate.HibernateUserDAO;
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

}
