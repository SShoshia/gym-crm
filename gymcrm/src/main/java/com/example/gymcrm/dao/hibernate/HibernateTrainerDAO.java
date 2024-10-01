package com.example.gymcrm.dao.hibernate;

import com.example.gymcrm.dao.core.TrainerDAO;
import com.example.gymcrm.model.entity.Trainer;
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
public class HibernateTrainerDAO implements TrainerDAO {

    private static final Logger logger = LoggerFactory.getLogger(HibernateTrainerDAO.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public HibernateTrainerDAO() {
        logger.info("Initialized HibernateTrainerDAO");
    }

    @Override
    public void create(Trainer trainer) {
        entityManager.persist(trainer);
        logger.info("Created Trainer {}", trainer);
    }

    @Override
    public Optional<Trainer> findById(Long id) {
        val res = Optional.ofNullable(entityManager.find(Trainer.class, id));
        logger.info("Searched trainer by id {}. found: {}.", id, res.isPresent() ? "yes" : "no");
        return res;
    }

    @Override
    public List<Trainer> findAll() {
        val res = entityManager.createQuery("from Trainer", Trainer.class).getResultList();
        logger.info("Searched all Trainers. found {}", res.size());
        return res;
    }

    @Override
    public void update(Trainer trainer) {
        entityManager.merge(trainer);
        logger.info("Updated Trainer {}", trainer);
    }

    @Override
    public void delete(Long id) {
        findById(id).ifPresent(entityManager::remove);
        logger.info("Deleted Trainer {}", id);
    }
}
