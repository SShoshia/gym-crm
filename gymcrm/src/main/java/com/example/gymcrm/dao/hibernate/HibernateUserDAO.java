package com.example.gymcrm.dao.hibernate;

import com.example.gymcrm.dao.core.UserDAO;
import com.example.gymcrm.model.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class HibernateUserDAO implements UserDAO {

    private static final Logger logger = LoggerFactory.getLogger(HibernateUserDAO.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public HibernateUserDAO() {
        logger.info("Initialized HibernateUserDAO");
    }

    @Override
    public User create(User user) {
        entityManager.persist(user);
        entityManager.flush();
        logger.info("Created user {}", user);
        return user;
    }

    @Override
    public Optional<User> findById(Long id) {
        val res = Optional.ofNullable(entityManager.find(User.class, id));
        logger.info("Searched user by id {}. found: {}.", id, res.isPresent() ? "yes" : "no");
        return res;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        val query = entityManager.createQuery(
                "SELECT u FROM User u WHERE u.username = :username", User.class);
        query.setParameter("username", username);

        Optional<User> res = Optional.empty();
        try {
            res = Optional.of(query.getSingleResult());
        } catch (NoResultException ignored) {
        }

        logger.info("Searched user by username {}. found: {}.", username, res.isPresent() ? "yes" : "no");
        return res;
    }

    @Override
    public List<User> findAll() {
        val res = entityManager.createQuery("FROM User", User.class).getResultList();
        logger.info("Searched all users. found {}", res.size());
        return res;
    }

    @Override
    public void update(User user) {
        entityManager.merge(user);
        logger.info("Updated User {}", user);
    }

    @Override
    public void delete(Long id) {
        findById(id).ifPresent(entityManager::remove);
        logger.info("Deleted User {}", id);
    }
}
