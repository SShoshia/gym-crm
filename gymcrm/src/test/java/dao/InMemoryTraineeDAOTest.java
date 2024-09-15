package dao;

import com.example.gymcrm.dao.inmemory.InMemoryTraineeDAO;
import com.example.gymcrm.model.Trainee;
import com.example.gymcrm.model.User;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.jupiter.api.Assertions.*;

public class InMemoryTraineeDAOTest {

    private InMemoryTraineeDAO traineeDAO;
    private Map<Long, Trainee> traineeStorage;

    private Trainee sampleTrainee1;
    private Trainee sampleTrainee2;

    @BeforeEach
    public void setup() {
        traineeStorage = new ConcurrentHashMap<>();
        traineeDAO = new InMemoryTraineeDAO(traineeStorage);

        val sampleUser1 = new User();
        val sampleUser2 = new User();
        sampleUser1.setId(1L);
        sampleUser2.setId(2L);

        sampleTrainee1 = new Trainee();
        sampleTrainee1.setId(1L);
        sampleTrainee1.setUser(sampleUser1);
        sampleTrainee1.setAddress("1 example street");
        sampleTrainee2 = new Trainee();
        sampleTrainee2.setId(2L);
        sampleTrainee2.setUser(sampleUser2);
        sampleTrainee2.setAddress("2 example street");
    }

    @Test
    public void testCreateTrainee() {
        traineeDAO.create(sampleTrainee1);
        assertNotNull(sampleTrainee1.getId());
        assertEquals(1, traineeStorage.size());
    }

    @Test
    public void testFindById() {
        traineeDAO.create(sampleTrainee1);
        val foundTrainee = traineeDAO.findById(sampleTrainee1.getId());
        assertTrue(foundTrainee.isPresent());
        assertEquals(1L, foundTrainee.get().getUserId());
    }

    @Test
    public void testFindAll() {
        traineeDAO.create(sampleTrainee1);
        traineeDAO.create(sampleTrainee2);
        val allTrainees = traineeDAO.findAll();
        assertEquals(2, allTrainees.size());
        assertEquals(1L, allTrainees.get(0).getUserId());
        assertEquals(2L, allTrainees.get(1).getUserId());
    }

    @Test
    public void testUpdateTrainee() {
        traineeDAO.create(sampleTrainee1);
        sampleTrainee1.setAddress("void");
        traineeDAO.update(sampleTrainee1);

        val updatedTrainee = traineeDAO.findById(sampleTrainee1.getId());
        assertTrue(updatedTrainee.isPresent());
        assertEquals("void", updatedTrainee.get().getAddress());
    }

    @Test
    public void testDeleteTrainee() {
        traineeDAO.create(sampleTrainee1);
        val id = sampleTrainee1.getId();

        traineeDAO.delete(id);
        assertFalse(traineeDAO.findById(id).isPresent());
    }

    @Test
    public void testFindByIdNonExistent() {
        val trainee = traineeDAO.findById(999L);
        assertFalse(trainee.isPresent());
    }
}
