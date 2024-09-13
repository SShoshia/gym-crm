package dao;

import com.example.gymcrm.dao.inmemory.InMemoryTrainingDAO;
import com.example.gymcrm.model.Training;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.jupiter.api.Assertions.*;

public class InMemoryTrainingDAOTest {

    private InMemoryTrainingDAO trainingDAO;
    private Map<Long, Training> trainingStorage;

    private Training sampleTraining1;
    private Training sampleTraining2;

    @BeforeEach
    public void setup() {
        trainingStorage = new ConcurrentHashMap<>();
        trainingDAO = new InMemoryTrainingDAO(trainingStorage);

        sampleTraining1 = new Training();
        sampleTraining1.setTrainingName("training 1");
        sampleTraining1.setTraineeId(1L);
        sampleTraining1.setTrainerId(2L);

        sampleTraining2 = new Training();
        sampleTraining2.setTrainingName("training 2");
        sampleTraining2.setTraineeId(2L);
        sampleTraining2.setTrainerId(1L);
    }

    @Test
    public void testCreateTraining() {
        trainingDAO.create(sampleTraining1);
        assertNotNull(sampleTraining1.getId());
        assertEquals(1, trainingStorage.size());
    }

    @Test
    public void testFindById() {
        trainingDAO.create(sampleTraining1);
        val foundTraining = trainingDAO.findById(sampleTraining1.getId());
        assertTrue(foundTraining.isPresent());
        assertEquals(1L, foundTraining.get().getId());
    }

    @Test
    public void testFindAll() {
        trainingDAO.create(sampleTraining1);
        trainingDAO.create(sampleTraining2);
        val allTrainings = trainingDAO.findAll();
        assertEquals(2, allTrainings.size());
        assertEquals(1L, allTrainings.get(0).getId());
        assertEquals(2L, allTrainings.get(1).getId());
    }

    @Test
    public void testDeleteTraining() {
        trainingDAO.create(sampleTraining1);
        val id = sampleTraining1.getId();

        trainingDAO.delete(id);
        assertFalse(trainingDAO.findById(id).isPresent());
    }

    @Test
    public void testFindByIdNonExistent() {
        val training = trainingDAO.findById(999L);
        assertFalse(training.isPresent());
    }
}
