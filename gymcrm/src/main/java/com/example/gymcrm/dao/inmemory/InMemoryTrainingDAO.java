package com.example.gymcrm.dao.inmemory;

import com.example.gymcrm.dao.core.TrainingDAO;
import com.example.gymcrm.model.Trainee;
import com.example.gymcrm.model.Trainer;
import com.example.gymcrm.model.Training;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryTrainingDAO implements TrainingDAO {

    private static final Logger logger = LoggerFactory.getLogger(InMemoryTrainingDAO.class);

    private final Map<Long, Trainer> trainerStorage;
    private final Map<Long, Trainee> traineeStorage;
    private final Map<Long, Training> trainingStorage;
    private final AtomicLong idCounter = new AtomicLong(1);

    @Autowired
    public InMemoryTrainingDAO(Map<Long, Trainer> trainerStorage, Map<Long, Trainee> traineeStorage, Map<Long, Training> trainingStorage) {
        this.trainerStorage = trainerStorage;
        this.traineeStorage = traineeStorage;
        this.trainingStorage = trainingStorage;
        logger.info("Initialized InMemoryTrainingDAO");
    }

    @Override
    public synchronized void create(Training training) {
        if (traineeStorage.containsKey(training.getTraineeId()) && trainerStorage.containsKey(training.getTrainerId())) {
            Long id = idCounter.getAndIncrement();
            while (trainingStorage.containsKey(id)) {
                id = idCounter.getAndIncrement();
            }
            training.setId(id);
            trainingStorage.put(id, training);
            logger.info("Created Training {}", training);
        } else {
            if (!traineeStorage.containsKey(training.getTrainerId())) {
                logger.info("Trainee with id {} not found. Training: {}", training.getTraineeId(), training);
            }
            if (!trainerStorage.containsKey(training.getTrainerId())) {
                logger.info("Trainer with id {} not found. Training: {}", training.getTrainerId(), training);
            }
        }
    }

    @Override
    public Optional<Training> findById(Long id) {
        val res = Optional.ofNullable(trainingStorage.get(id));
        logger.info("Searched training by id {}. found: {}.", id, res.isPresent() ? "yes" : "no");
        return res;
    }

    @Override
    public List<Training> findAll() {
        val res = new ArrayList<>(trainingStorage.values());
        logger.info("Searched all Trainings. found {}", res.size());
        return res;
    }

    @Override
    public synchronized void delete(Long id) {
        trainingStorage.remove(id);
        logger.info("Deleted Training {}", id);
    }
}
