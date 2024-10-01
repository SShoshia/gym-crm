package com.example.gymcrm.dao.inmemory;

import com.example.gymcrm.dao.core.UserDAO;
import com.example.gymcrm.model.entity.User;
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
public class InMemoryUserDAO implements UserDAO {

    private static final Logger logger = LoggerFactory.getLogger(InMemoryUserDAO.class);

    private final Map<Long, User> userStorage;

    private final AtomicLong idCounter = new AtomicLong(1);

    @Autowired
    public InMemoryUserDAO(Map<Long, User> userStorage) {
        this.userStorage = userStorage;
        logger.info("Initialized InMemoryUserDAO");
    }

    @Override
    public synchronized void create(User user) {
        Long id = idCounter.getAndIncrement();
        while (userStorage.containsKey(id)) {
            id = idCounter.getAndIncrement();
        }
        user.setId(id);

        userStorage.put(id, user);
        logger.info("Created user {}", user);
    }

    @Override
    public Optional<User> findById(Long id) {
        val res = Optional.ofNullable(userStorage.get(id));
        logger.info("Searched user by id {}. found: {}.", id, res.isPresent() ? "yes" : "no");
        return res;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        val res = userStorage.values().stream().filter(u -> u.getUsername().equals(username)).findFirst();
        logger.info("Searched user by username {}. found: {}.", username, res.isPresent() ? "yes" : "no");
        return res;
    }

    @Override
    public List<User> findAll() {
        val res = new ArrayList<>(userStorage.values());
        logger.info("Searched all users. found {}", res.size());
        return res;
    }

    @Override
    public synchronized void update(User user) {
        userStorage.put(user.getId(), user);
        logger.info("Updated User {}", user);
    }

    @Override
    public void delete(Long id) {
        userStorage.remove(id);
        logger.info("Deleted User {}", id);
    }

}
