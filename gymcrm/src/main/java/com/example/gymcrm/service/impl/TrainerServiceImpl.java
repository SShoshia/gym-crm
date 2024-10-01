package com.example.gymcrm.service.impl;

import com.example.gymcrm.auth.Authenticator;
import com.example.gymcrm.dao.core.TrainerDAO;
import com.example.gymcrm.dao.core.UserDAO;
import com.example.gymcrm.model.Trainer;
import com.example.gymcrm.model.criteria.TrainerSearchCriteria;
import com.example.gymcrm.service.core.TrainerService;
import jakarta.transaction.Transactional;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.example.gymcrm.util.ExceptionUtil.error;

@Transactional
@Service
public class TrainerServiceImpl implements TrainerService {

    private static final Logger logger = LoggerFactory.getLogger(TrainerServiceImpl.class);

    private final TrainerDAO trainerDAO;
    private final UserDAO userDAO;
    private final Authenticator authenticator;

    @Autowired
    public TrainerServiceImpl(TrainerDAO trainerDAO, UserDAO userDAO, Authenticator authenticator) {
        this.trainerDAO = trainerDAO;
        this.userDAO = userDAO;
        this.authenticator = authenticator;
    }

    @Override
    public void createTrainer(Trainer trainer) throws Exception {
        if (trainer.getUser() == null || trainer.getSpecialization() == null) {
            error(logger, "Create trainer - Provided trainer has null field(s). Trainer: " + trainer, IllegalArgumentException.class);
        }

        if (userDAO.findById(trainer.getUserId()).isPresent()) {
            trainerDAO.create(trainer);
        } else {
            error(logger, "User with provided userId not found. Trainer: " + trainer, RuntimeException.class);
        }
    }

    @Override
    public Optional<Trainer> getTrainer(Long id) {
        return trainerDAO.findById(id);
    }

    @Override
    public List<Trainer> getAllTrainers() {
        return trainerDAO.findAll();
    }

    @Override
    public void updateTrainer(Trainer trainer) throws Exception {
        if (trainer.getUser() == null || trainer.getSpecialization() == null) {
            error(logger, "Update trainer - Provided trainer has null field(s). Trainer: " + trainer, IllegalArgumentException.class);
        }

        if (!authenticator.authenticate(trainer.getUser())) {
            error(logger, "Update trainer - Provided user is not authenticated. Trainer: " + trainer, IllegalArgumentException.class);
        }

        if (trainer.getId() != null && trainerDAO.findById(trainer.getId()).isPresent() &&
                trainer.getUserId() != null && userDAO.findById(trainer.getUserId()).isPresent()) {
            trainerDAO.update(trainer);
        } else {
            error(logger, "Error while updating trainer. Trainer: " + trainer, RuntimeException.class);
        }
    }

    @Override
    public void deleteTrainer(Trainer trainer) throws Exception {
        if (!authenticator.authenticate(trainer.getUser())) {
            error(logger, "Delete trainer - Provided user is not authenticated.", RuntimeException.class);
        }

        trainerDAO.delete(trainer.getId());
    }

    @Override
    public List<Trainer> getTrainersMatchingCriteria(TrainerSearchCriteria criteria) {
        return criteria.getTraineeUsernameNotAssigned() == null ? trainerDAO.findAll()
                : trainerDAO.findAll().stream().filter(trainer ->
                trainer.getTrainings().stream().noneMatch(training ->
                        training.getTraineeUsername().equals(criteria.getTraineeUsernameNotAssigned())
                )
        ).toList();
    }

    @Override
    public Optional<Trainer> getTrainerByUsername(String username) {
        val res = trainerDAO.findAll().stream().filter(t -> t.getUsername().equals(username)).findFirst();
        logger.info("Searched trainer by username {}. found: {}.", username, res.isPresent() ? "yes" : "no");
        return res;
    }
}
