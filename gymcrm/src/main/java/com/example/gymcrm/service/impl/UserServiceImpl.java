package com.example.gymcrm.service.impl;

import com.example.gymcrm.dao.core.UserDAO;
import com.example.gymcrm.model.User;
import com.example.gymcrm.service.core.UserService;
import com.example.gymcrm.util.UserUtils;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Transactional
@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserDAO userDAO;

    private final AtomicLong usernameCounter = new AtomicLong(1);

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public void createUser(User user) {
        String username = UserUtils.generateUsername(user.getFirstName(), user.getLastName());
        boolean usernameExists = userDAO.findByUsername(username).isPresent();
        if (usernameExists) {
            username = username + usernameCounter.getAndIncrement();
        }
        user.setUsername(username);
        logger.info("Set username to {} for pending user.", username);

        user.setPassword(UserUtils.generatePassword());
        logger.info("Set random {}-symbol password for pending user.", user.getPassword().length());

        userDAO.create(user);
    }

    @Override
    public Optional<User> getUser(Long id) {
        return userDAO.findById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userDAO.findAll();
    }

    @Override
    public void updateUser(User user) {
        if (user.getId() != null && getUser(user.getId()).isPresent()) {
            userDAO.update(user);
        } else {
            error("Update user: user with specified ID not found. ID: " + user.getId());
        }
    }

    @Override
    public void deleteUser(Long id) {
        if (getUser(id).isPresent()) {
            userDAO.delete(id);
        } else {
            error("Delete user: user with specified ID not found. ID: " + id);
        }
    }

    private void error(String errorMessage) {
        logger.error(errorMessage);
        throw new RuntimeException(errorMessage);
    }
}
