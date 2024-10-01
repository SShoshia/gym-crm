package com.example.gymcrm.datainitialization;

import com.example.gymcrm.fileservice.FileService;
import com.example.gymcrm.model.entity.Trainee;
import com.example.gymcrm.model.entity.Trainer;
import com.example.gymcrm.model.entity.Training;
import com.example.gymcrm.model.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Component
public class DataInitializer {

    private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);

    private final FileService fileService;
    private final ObjectMapper objectMapper;

    @Autowired
    public DataInitializer(FileService fileService, ObjectMapper objectMapper) {
        this.fileService = fileService;
        this.objectMapper = objectMapper;
    }

    public void loadUsersFromFile(String filePath, Map<Long, User> storage) {
        try {
            String jsonData = fileService.readFileFromResources(filePath);
            List<?> dataList = objectMapper.readValue(jsonData, List.class);

            for (Object data : dataList) {
                User user = objectMapper.convertValue(data, User.class);
                storage.put(user.getId(), user);
            }
            logger.info("Loaded users from file.");
        } catch (IOException e) {
            logger.error("Error when loading users from file: {}", e.getMessage());
        }
    }

    public void loadTraineesFromFile(String filePath, Map<Long, Trainee> storage) {
        try {
            String jsonData = fileService.readFileFromResources(filePath);
            List<?> dataList = objectMapper.readValue(jsonData, List.class);

            for (Object data : dataList) {
                Trainee trainee = objectMapper.convertValue(data, Trainee.class);
                storage.put(trainee.getId(), trainee);
            }
            logger.info("Loaded trainees from file.");
        } catch (IOException e) {
            logger.error("Error when loading trainees from file: {}", e.getMessage());
        }
    }

    public void loadTrainersFromFile(String filePath, Map<Long, Trainer> storage) {
        try {
            String jsonData = fileService.readFileFromResources(filePath);
            List<?> dataList = objectMapper.readValue(jsonData, List.class);

            for (Object data : dataList) {
                Trainer trainer = objectMapper.convertValue(data, Trainer.class);
                storage.put(trainer.getId(), trainer);
            }
            logger.info("Loaded trainers from file.");
        } catch (IOException e) {
            logger.error("Error when loading trainers from file: {}", e.getMessage());
        }
    }

    public void loadTrainingsFromFile(String filePath, Map<Long, Training> storage) {
        try {
            String jsonData = fileService.readFileFromResources(filePath);
            List<?> dataList = objectMapper.readValue(jsonData, List.class);

            for (Object data : dataList) {
                Training training = objectMapper.convertValue(data, Training.class);
                storage.put(training.getId(), training);
            }
            logger.info("Loaded trainings from file.");
        } catch (IOException e) {
            logger.error("Error when loading trainings from file: {}", e.getMessage());
        }
    }

}
