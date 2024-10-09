package com.example;

import com.example.gymcrm.config.AppConfig;
import com.example.gymcrm.facade.GymCRMFacade;
import com.example.gymcrm.model.entity.Trainee;
import com.example.gymcrm.model.entity.Trainer;
import com.example.gymcrm.model.entity.Training;
import com.example.gymcrm.model.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        GymCRMFacade facade = context.getBean(GymCRMFacade.class);

        logger.info("Started Gym CRM application.");

        User user = new User();
        user.setFirstName("New");
        user.setLastName("User");

        facade.createUser(user);

        Trainee trainee = new Trainee();
        trainee.setUser(user);
        trainee.setAddress("Example Address");
        trainee.setDateOfBirth(LocalDate.now());

        facade.createTrainee(trainee);

        Trainer trainer = new Trainer();
        trainer.setUser(facade.getUser(1L).get());
        trainer.setSpecialization("Specialization");

        facade.createTrainer(trainer);

        Training training = new Training();
        training.setTrainee(trainee);
        training.setTrainer(trainer);
        training.setTrainingName("Training Name");
        training.setTrainingDate(LocalDate.now());
        training.setTrainingDuration(5);
        training.setTrainingName("Training Name");

        facade.createTraining(training);

        logger.info("Initialized data for Gym CRM application.");

        logger.info("All users: {}", facade.getAllUsers());
        logger.info("All trainees: {}", facade.getAllTrainees());
        logger.info("All trainers: {}", facade.getAllTrainers());
        logger.info("All trainings: {}", facade.getAllTrainings());

        context.close();
    }
}