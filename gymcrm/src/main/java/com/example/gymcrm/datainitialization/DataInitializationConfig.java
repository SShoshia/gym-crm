package com.example.gymcrm.datainitialization;

import com.example.gymcrm.model.Trainee;
import com.example.gymcrm.model.Trainer;
import com.example.gymcrm.model.Training;
import com.example.gymcrm.model.User;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class DataInitializationConfig {

    private final Map<Long, User> userStorage;
    private final Map<Long, Trainee> traineeStorage;
    private final Map<Long, Trainer> trainerStorage;
    private final Map<Long, Training> trainingStorage;
    private final DataInitializer dataInitializer;

    @Autowired
    public DataInitializationConfig(
            Map<Long, User> userStorage,
            Map<Long, Trainee> traineeStorage,
            Map<Long, Trainer> trainerStorage,
            Map<Long, Training> trainingStorage,
            DataInitializer dataInitializer
    ) {
        this.userStorage = userStorage;
        this.traineeStorage = traineeStorage;
        this.trainerStorage = trainerStorage;
        this.trainingStorage = trainingStorage;
        this.dataInitializer = dataInitializer;
    }

    @Value("${data.file.path.users}")
    private String userFilePath;
    @Value("${data.file.path.trainees}")
    private String traineeFilePath;
    @Value("${data.file.path.trainers}")
    private String trainerFilePath;
    @Value("${data.file.path.trainings}")
    private String trainingFilePath;

    @PostConstruct
    public void init() {
        dataInitializer.loadUsersFromFile(userFilePath, userStorage);
        dataInitializer.loadTraineesFromFile(traineeFilePath, traineeStorage);
        dataInitializer.loadTrainersFromFile(trainerFilePath, trainerStorage);
        dataInitializer.loadTrainingsFromFile(trainingFilePath, trainingStorage);
    }

}
