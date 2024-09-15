package com.example;

import com.example.gymcrm.config.AppConfig;
import com.example.gymcrm.facade.GymCRMFacade;
import com.example.gymcrm.model.Trainee;
import com.example.gymcrm.model.Trainer;
import com.example.gymcrm.model.Training;
import com.example.gymcrm.model.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        GymCRMFacade facade = context.getBean(GymCRMFacade.class);

        System.out.println("Started Gym CRM application.");

        User user = new User();
        user.setFirstName("New");
        user.setLastName("User");

        facade.createUser(user);

        Trainee trainee = new Trainee();
        trainee.setUserId(user.getId());
        trainee.setAddress("Example Address");
        trainee.setDateOfBirth(new Date());

        facade.createTrainee(trainee);

        Trainer trainer = new Trainer();
        trainer.setUserId(1L);
        trainer.setSpecialization("Specialization");

        facade.createTrainer(trainer);

        Training training = new Training();
        training.setTraineeId(trainee.getId());
        training.setTrainerId(trainer.getId());
        training.setTrainingName("Training Name");
        training.setTrainingDate(new Date());
        training.setTrainingDuration(5);
        training.setTrainingName("Training Name");

        facade.createTraining(training);

        System.out.println("Initialized data for Gym CRM application.");

        System.out.println("All users: " + facade.getAllUsers());
        System.out.println("All trainees: " + facade.getAllTrainees());
        System.out.println("All trainers: " + facade.getAllTrainers());
        System.out.println("All trainings: " + facade.getAllTrainings());

        context.close();
    }
}