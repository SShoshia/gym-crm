package com.example;

import com.example.gymcrm.config.AppConfig;
import com.example.gymcrm.facade.GymCRMFacade;
import com.example.gymcrm.model.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        GymCRMFacade facade = context.getBean(GymCRMFacade.class);

        User user = new User();
        user.setFirstName("New");
        user.setLastName("User");

        facade.createUser(user);

        System.out.println("Started Gym CRM application. All users: " + facade.getAllUsers());

        context.close();
    }
}