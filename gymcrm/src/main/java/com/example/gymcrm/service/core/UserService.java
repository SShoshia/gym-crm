package com.example.gymcrm.service.core;

import com.example.gymcrm.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User createUser(User user);

    Optional<User> getUser(Long id);

    List<User> getAllUsers();

    void updateUser(User user);

    void deleteUser(Long id);

    Optional<User> getUserByUsername(String username);

}
