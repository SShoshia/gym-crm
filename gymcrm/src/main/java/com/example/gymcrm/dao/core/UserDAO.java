package com.example.gymcrm.dao.core;

import com.example.gymcrm.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDAO {

    User create(User user);

    Optional<User> findById(Long id);

    Optional<User> findByUsername(String username);

    List<User> findAll();

    void update(User user);

    void delete(Long id);

}
