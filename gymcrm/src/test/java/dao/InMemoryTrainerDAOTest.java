package dao;

import com.example.gymcrm.dao.inmemory.InMemoryTrainerDAO;
import com.example.gymcrm.model.Trainer;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.jupiter.api.Assertions.*;

public class InMemoryTrainerDAOTest {

    private InMemoryTrainerDAO trainerDAO;
    private Map<Long, Trainer> trainerStorage;

    private Trainer sampleTrainer1;
    private Trainer sampleTrainer2;

    @BeforeEach
    public void setup() {
        trainerStorage = new ConcurrentHashMap<>();
        trainerDAO = new InMemoryTrainerDAO(trainerStorage);

        sampleTrainer1 = new Trainer();
        sampleTrainer1.setId(1L);
        sampleTrainer1.setUserId(1L);
        sampleTrainer1.setSpecialization("spec 1");
        sampleTrainer2 = new Trainer();
        sampleTrainer2.setId(2L);
        sampleTrainer2.setUserId(2L);
        sampleTrainer2.setSpecialization("spec 2");
    }

    @Test
    public void testCreateTrainer() {
        trainerDAO.create(sampleTrainer1);
        assertNotNull(sampleTrainer1.getId());
        assertEquals(1, trainerStorage.size());
    }

    @Test
    public void testFindById() {
        trainerDAO.create(sampleTrainer1);
        val foundTrainer = trainerDAO.findById(sampleTrainer1.getId());
        assertTrue(foundTrainer.isPresent());
        assertEquals(1L, foundTrainer.get().getUserId());
    }

    @Test
    public void testFindAll() {
        trainerDAO.create(sampleTrainer1);
        trainerDAO.create(sampleTrainer2);
        val allTrainers = trainerDAO.findAll();
        assertEquals(2, allTrainers.size());
        assertEquals(1L, allTrainers.get(0).getUserId());
        assertEquals(2L, allTrainers.get(1).getUserId());
    }

    @Test
    public void testUpdateTrainer() {
        trainerDAO.create(sampleTrainer1);
        sampleTrainer1.setSpecialization("spec 3");
        trainerDAO.update(sampleTrainer1);

        val updatedTrainer = trainerDAO.findById(sampleTrainer1.getId());
        assertTrue(updatedTrainer.isPresent());
        assertEquals("spec 3", updatedTrainer.get().getSpecialization());
    }

    @Test
    public void testDeleteTrainer() {
        trainerDAO.create(sampleTrainer1);
        val id = sampleTrainer1.getId();

        trainerDAO.delete(id);
        assertFalse(trainerDAO.findById(id).isPresent());
    }

    @Test
    public void testFindByIdNonExistent() {
        val trainer = trainerDAO.findById(999L);
        assertFalse(trainer.isPresent());
    }
}
