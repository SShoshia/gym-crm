package com.example.gymcrm.dao.hibernate;

import com.example.gymcrm.dao.core.TrainingDAO;
import com.example.gymcrm.model.Training;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class HibernateTrainingDAO implements TrainingDAO {

    private static final Logger logger = LoggerFactory.getLogger(HibernateTrainingDAO.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public HibernateTrainingDAO() {
        logger.info("Initialized HibernateTrainingDAO");
    }

    @Override
    public void create(Training training) {
        entityManager.persist(training);
        logger.info("Created Training {}", training);
    }

    @Override
    public Optional<Training> findById(Long id) {
        val res = Optional.ofNullable(entityManager.find(Training.class, id));
        logger.info("Searched training by id {}. found: {}.", id, res.isPresent() ? "yes" : "no");
        return res;
    }

    @Override
    public List<Training> findAll() {
        val res = entityManager.createQuery("from Training", Training.class).getResultList();
        logger.info("Searched all Trainings. found {}", res.size());
        return res;
    }

    @Override
    public void delete(Long id) {
        findById(id).ifPresent(value -> entityManager.remove(value));
        logger.info("Deleted Training {}", id);
    }
}
