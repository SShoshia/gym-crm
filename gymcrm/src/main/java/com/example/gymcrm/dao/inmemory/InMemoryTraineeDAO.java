package com.example.gymcrm.dao.inmemory;

import com.example.gymcrm.dao.core.TraineeDAO;
import com.example.gymcrm.model.Trainee;
import com.example.gymcrm.model.User;
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
public class InMemoryTraineeDAO implements TraineeDAO {

    private static final Logger logger = LoggerFactory.getLogger(InMemoryTraineeDAO.class);

    private final Map<Long, User> userStorage;
    private final Map<Long, Trainee> traineeStorage;

    private final AtomicLong idCounter = new AtomicLong(1);

    @Autowired
    public InMemoryTraineeDAO(Map<Long, User> userStorage, Map<Long, Trainee> traineeStorage) {
        this.userStorage = userStorage;
        this.traineeStorage = traineeStorage;
        logger.info("Initialized InMemoryTraineeDAO");
    }

    @Override
    public synchronized void create(Trainee trainee) {
        if (userStorage.containsKey(trainee.getId())) {
            Long id = idCounter.getAndIncrement();
            while (traineeStorage.containsKey(id)) {
                id = idCounter.getAndIncrement();
            }
            trainee.setId(id);
            traineeStorage.put(id, trainee);
            logger.info("Created Trainee {}", trainee);
        } else {
            logger.error("User with userId not found. Trainee: {}", trainee);
        }
    }

    @Override
    public Optional<Trainee> findById(Long id) {
        val res = Optional.ofNullable(traineeStorage.get(id));
        logger.info("Searched trainee by id {}. found: {}.", id, res.isPresent() ? "yes" : "no");
        return res;
    }

    @Override
    public List<Trainee> findAll() {
        val res = new ArrayList<>(traineeStorage.values());
        logger.info("Searched all Trainees. found {}", res.size());
        return res;
    }

    @Override
    public synchronized void update(Trainee trainee) {
        if (trainee.getUserId() != null && traineeStorage.containsKey(trainee.getUserId())) {
            traineeStorage.put(trainee.getUserId(), trainee);
            logger.info("Updated Trainee {}", trainee);
        } else {
            logger.error("Trainee with specified ID not found. ID: {}", trainee.getId());
        }
    }

    @Override
    public synchronized void delete(Long id) {
        traineeStorage.remove(id);
        logger.info("Deleted Trainee {}", id);
    }

}
