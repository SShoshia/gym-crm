package com.example.gymcrm.service.impl;

import com.example.gymcrm.auth.Authenticator;
import com.example.gymcrm.dao.core.TraineeDAO;
import com.example.gymcrm.dao.core.UserDAO;
import com.example.gymcrm.model.Trainee;
import com.example.gymcrm.service.core.TraineeService;
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
public class TraineeServiceImpl implements TraineeService {

    private static final Logger logger = LoggerFactory.getLogger(TraineeServiceImpl.class);

    private final TraineeDAO traineeDAO;
    private final UserDAO userDAO;
    private final Authenticator authenticator;

    @Autowired
    public TraineeServiceImpl(TraineeDAO traineeDAO, UserDAO userDAO, Authenticator authenticator) {
        this.traineeDAO = traineeDAO;
        this.userDAO = userDAO;
        this.authenticator = authenticator;
    }

    @Override
    public void createTrainee(Trainee trainee) throws Exception {
        if (trainee.getUser() == null || trainee.getAddress() == null || trainee.getDateOfBirth() == null) {
            error(logger, "Create trainee - Provided trainee has null field(s). Trainee: " + trainee, IllegalArgumentException.class);
        }

        if (userDAO.findById(trainee.getUserId()).isPresent()) {
            traineeDAO.create(trainee);
        } else {
            error(logger, "User with provided userId not found. Trainee: " + trainee, RuntimeException.class);
        }
    }

    @Override
    public Optional<Trainee> getTrainee(Long id) {
        return traineeDAO.findById(id);
    }

    @Override
    public List<Trainee> getAllTrainees() {
        return traineeDAO.findAll();
    }

    @Override
    public void updateTrainee(Trainee trainee) throws Exception {
        if (trainee.getUser() == null || trainee.getAddress() == null || trainee.getDateOfBirth() == null) {
            error(logger, "Update trainee - Provided trainee has null field(s). Trainee: " + trainee, IllegalArgumentException.class);
        }

        if (!authenticator.authenticate(trainee.getUser())) {
            error(logger, "Update trainee - Provided user is not authenticated.", RuntimeException.class);
        }

        if (trainee.getId() != null && traineeDAO.findById(trainee.getId()).isPresent() &&
                trainee.getUserId() != null && userDAO.findById(trainee.getUserId()).isPresent()) {
            traineeDAO.update(trainee);
        } else {
            error(logger, "Error while updating trainee. Trainee: " + trainee, RuntimeException.class);
        }
    }

    @Override
    public void deleteTrainee(Trainee trainee) throws Exception {
        if (!authenticator.authenticate(trainee.getUser())) {
            error(logger, "Delete trainee - Provided user is not authenticated.", RuntimeException.class);
        }

        traineeDAO.delete(trainee.getId());
    }

    @Override
    public Optional<Trainee> getTraineeByUsername(String username) {
        val res = traineeDAO.findAll().stream().filter(t -> t.getUsername().equals(username)).findFirst();
        logger.info("Searched trainee by username {}. found: {}.", username, res.isPresent() ? "yes" : "no");
        return res;
    }

    @Override
    public void deleteTraineeByUsername(String username) {
        val trainee = getTraineeByUsername(username);
        trainee.ifPresent(value -> traineeDAO.delete(value.getId()));
    }
}
