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
    private Map<Long, User> userStorage;
    private Map<Long, Trainee> traineeStorage;

    private User sampleUser1;
    private User sampleUser2;

    private Trainee sampleTrainee1;
    private Trainee sampleTrainee2;

    @BeforeEach
    public void setup() {
        userStorage = new ConcurrentHashMap<>();
        traineeStorage = new ConcurrentHashMap<>();
        traineeDAO = new InMemoryTraineeDAO(userStorage, traineeStorage);

        sampleUser1 = new User();
        sampleUser1.setId(1L);
        sampleUser1.setFirstName("John");
        sampleUser1.setLastName("Doe");
        sampleUser2 = new User();
        sampleUser2.setId(2L);
        sampleUser2.setFirstName("Jane");
        sampleUser2.setLastName("Doe");

        sampleTrainee1 = new Trainee();
        sampleTrainee1.setId(1L);
        sampleTrainee1.setUserId(1L);
        sampleTrainee1.setAddress("1 example street");
        sampleTrainee2 = new Trainee();
        sampleTrainee2.setId(2L);
        sampleTrainee2.setUserId(2L);
        sampleTrainee2.setAddress("2 example street");
    }

    @Test
    public void testCreateTrainee() {
        userStorage.put(1L, sampleUser1);
        traineeDAO.create(sampleTrainee1);
        assertNotNull(sampleTrainee1.getId());
        assertEquals(1, traineeStorage.size());
    }

    @Test
    public void testCreateTraineeWithNoUser() {
        traineeDAO.create(sampleTrainee1);
        assertEquals(0, traineeStorage.size());
    }

    @Test
    public void testFindById() {
        userStorage.put(1L, sampleUser1);
        traineeDAO.create(sampleTrainee1);
        val foundTrainee = traineeDAO.findById(sampleTrainee1.getId());
        assertTrue(foundTrainee.isPresent());
        assertEquals(1L, foundTrainee.get().getUserId());
    }

    @Test
    public void testFindAll() {
        userStorage.put(1L, sampleUser1);
        userStorage.put(2L, sampleUser2);
        traineeDAO.create(sampleTrainee1);
        traineeDAO.create(sampleTrainee2);
        val allTrainees = traineeDAO.findAll();
        assertEquals(2, allTrainees.size());
        assertEquals(1L, allTrainees.get(0).getUserId());
        assertEquals(2L, allTrainees.get(1).getUserId());
    }

    @Test
    public void testUpdateTrainee() {
        userStorage.put(1L, sampleUser1);
        traineeDAO.create(sampleTrainee1);
        sampleTrainee1.setAddress("void");
        traineeDAO.update(sampleTrainee1);

        val updatedTrainee = traineeDAO.findById(sampleTrainee1.getId());
        assertTrue(updatedTrainee.isPresent());
        assertEquals("void", updatedTrainee.get().getAddress());
    }

    @Test
    public void testDeleteTrainee() {
        userStorage.put(1L, sampleUser1);
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
