package com.example.gymcrm.dao.hibernate;

import com.example.gymcrm.dao.core.TraineeDAO;
import com.example.gymcrm.model.entity.Trainee;
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
public class HibernateTraineeDAO implements TraineeDAO {

    private static final Logger logger = LoggerFactory.getLogger(HibernateTraineeDAO.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public HibernateTraineeDAO() {
        logger.info("Initialized HibernateTraineeDAO");
    }

    public HibernateTraineeDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void create(Trainee trainee) {
        entityManager.persist(trainee);
        logger.info("Created Trainee {}", trainee);
    }

    @Override
    public Optional<Trainee> findById(Long id) {
        val res = Optional.ofNullable(entityManager.find(Trainee.class, id));
        logger.info("Searched trainee by id {}. found: {}.", id, res.isPresent() ? "yes" : "no");
        return res;
    }

    @Override
    public List<Trainee> findAll() {
        val res = entityManager.createQuery("FROM Trainee", Trainee.class).getResultList();
        logger.info("Searched all Trainees. found {}", res.size());
        return res;
    }

    @Override
    public Trainee update(Trainee trainee) {
        val res = entityManager.merge(trainee);
        logger.info("Updated Trainee {}", trainee);
        return res;
    }

    @Override
    public void delete(Long id) {
        findById(id).ifPresent(entityManager::remove);
        logger.info("Deleted Trainee {}", id);
    }
}
