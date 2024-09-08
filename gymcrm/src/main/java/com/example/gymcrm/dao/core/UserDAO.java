package com.example.gymcrm.dao.core;

import com.example.gymcrm.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDAO {

    void create(User user);

    Optional<User> findById(Long id);

    Optional<User> findByUsername(String username);

    List<User> findAll();

    void update(User user);

    void delete(Long id);

}
