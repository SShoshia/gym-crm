package com.example.gymcrm.dao.inmemory;

import com.example.gymcrm.dao.core.TrainerDAO;
import com.example.gymcrm.model.Trainer;
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
public class InMemoryTrainerDAO implements TrainerDAO {

    private static final Logger logger = LoggerFactory.getLogger(InMemoryTrainerDAO.class);

    private final Map<Long, Trainer> trainerStorage;
    private final AtomicLong idCounter = new AtomicLong(1);

    @Autowired
    public InMemoryTrainerDAO(Map<Long, Trainer> trainerStorage) {
        this.trainerStorage = trainerStorage;
        logger.info("Initialized InMemoryTrainerDAO");
    }

    @Override
    public synchronized void create(Trainer trainer) {
        Long id = idCounter.getAndIncrement();
        while (trainerStorage.containsKey(id)) {
            id = idCounter.getAndIncrement();
        }
        trainer.setId(id);
        trainerStorage.put(id, trainer);
        logger.info("Created Trainer {}", trainer);
    }

    @Override
    public Optional<Trainer> findById(Long id) {
        val res = Optional.ofNullable(trainerStorage.get(id));
        logger.info("Searched trainer by id {}. found: {}.", id, res.isPresent() ? "yes" : "no");
        return res;
    }

    @Override
    public List<Trainer> findAll() {
        val res = new ArrayList<>(trainerStorage.values());
        logger.info("Searched all Trainers. found {}", res.size());
        return res;
    }

    @Override
    public synchronized void update(Trainer trainer) {
        if (trainer.getUserId() != null && trainerStorage.containsKey(trainer.getUserId())) {
            trainerStorage.put(trainer.getUserId(), trainer);
            logger.info("Updated Trainer {}", trainer);
        } else {
            logger.error("Trainer with specified ID not found. ID: {}", trainer.getId());
        }
    }

    @Override
    public synchronized void delete(Long id) {
        trainerStorage.remove(id);
        logger.info("Deleted Trainer {}", id);
    }
}
