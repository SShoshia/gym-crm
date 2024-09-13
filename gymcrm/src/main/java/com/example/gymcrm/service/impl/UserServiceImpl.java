package com.example.gymcrm.service.impl;

import com.example.gymcrm.dao.core.UserDAO;
import com.example.gymcrm.model.User;
import com.example.gymcrm.service.core.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public void createUser(User user) {
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
        userDAO.update(user);
    }

    @Override
    public void deleteUser(Long id) {
        userDAO.delete(id);
    }
}
