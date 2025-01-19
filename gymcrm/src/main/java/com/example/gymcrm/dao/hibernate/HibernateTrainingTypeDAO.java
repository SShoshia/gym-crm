package com.example.gymcrm.dao.hibernate;

import com.example.gymcrm.dao.core.TrainingTypeDAO;
import com.example.gymcrm.model.entity.TrainingType;
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
public class HibernateTrainingTypeDAO implements TrainingTypeDAO {

    private static final Logger logger = LoggerFactory.getLogger(HibernateTrainingTypeDAO.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public HibernateTrainingTypeDAO() {
        logger.info("Initialized HibernateTrainingTypeDAO");
    }

    @Override
    public void create(TrainingType trainingType) {
        entityManager.persist(trainingType);
        logger.info("Created training type {}", trainingType);
    }

    @Override
    public Optional<TrainingType> findById(Long id) {
        val res = Optional.ofNullable(entityManager.find(TrainingType.class, id));
        logger.info("Searched training type by id {}. found: {}.", id, res.isPresent() ? "yes" : "no");
        return res;
    }

    @Override
    public Optional<TrainingType> findByName(String name) {
        val query = entityManager.createQuery("SELECT t from TrainingType t WHERE t.name = :name", TrainingType.class);
        query.setParameter("name", name);

        Optional<TrainingType> res = Optional.empty();
        try {
            res = Optional.of(query.getSingleResult());
        } catch (NoResultException ignored) {
        }

        logger.info("Searched training type by name {}. found: {}.", name, res.isPresent() ? "yes" : "no");
        return res;
    }

    @Override
    public List<TrainingType> findAll() {
        val res = entityManager.createQuery("from Training", TrainingType.class).getResultList();
        logger.info("Searched all training types. found {}", res.size());
        return res;
    }

    @Override
    public void delete(Long id) {
        findById(id).ifPresent(entityManager::remove);
        logger.info("Removed training type {}", id);
    }
}
