package dao;

import com.example.gymcrm.dao.inmemory.InMemoryTrainingDAO;
import com.example.gymcrm.model.Trainee;
import com.example.gymcrm.model.Trainer;
import com.example.gymcrm.model.Training;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.jupiter.api.Assertions.*;

public class InMemoryTrainingDAOTest {

    private InMemoryTrainingDAO trainingDAO;
    private Map<Long, Trainer> trainerStorage;
    private Map<Long, Trainee> traineeStorage;
    private Map<Long, Training> trainingStorage;

    private Trainer sampleTrainer1;
    private Trainer sampleTrainer2;

    private Trainee sampleTrainee1;
    private Trainee sampleTrainee2;

    private Training sampleTraining1;
    private Training sampleTraining2;

    @BeforeEach
    public void setup() {
        trainerStorage = new ConcurrentHashMap<>();
        traineeStorage = new ConcurrentHashMap<>();
        trainingStorage = new ConcurrentHashMap<>();
        trainingDAO = new InMemoryTrainingDAO(trainerStorage, traineeStorage, trainingStorage);

        sampleTrainer1 = new Trainer();
        sampleTrainer1.setId(1L);
        sampleTrainer1.setUserId(1L);
        sampleTrainer1.setSpecialization("spec 1");
        sampleTrainer2 = new Trainer();
        sampleTrainer2.setId(2L);
        sampleTrainer2.setUserId(2L);
        sampleTrainer2.setSpecialization("spec 2");

        sampleTrainee1 = new Trainee();
        sampleTrainee1.setId(1L);
        sampleTrainee1.setUserId(1L);
        sampleTrainee1.setAddress("1 example street");
        sampleTrainee2 = new Trainee();
        sampleTrainee2.setId(2L);
        sampleTrainee2.setUserId(2L);
        sampleTrainee2.setAddress("2 example street");

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
        traineeStorage.put(1L, sampleTrainee1);
        trainerStorage.put(2L, sampleTrainer2);
        trainingDAO.create(sampleTraining1);
        assertNotNull(sampleTraining1.getTrainingId());
        assertEquals(1, trainingStorage.size());
    }

    @Test
    public void testFindById() {
        traineeStorage.put(1L, sampleTrainee1);
        trainerStorage.put(2L, sampleTrainer2);
        trainingDAO.create(sampleTraining1);
        val foundTraining = trainingDAO.findById(sampleTraining1.getTrainingId());
        assertTrue(foundTraining.isPresent());
        assertEquals(1L, foundTraining.get().getTrainingId());
    }

    @Test
    public void testFindAll() {
        traineeStorage.put(1L, sampleTrainee1);
        trainerStorage.put(2L, sampleTrainer2);
        traineeStorage.put(2L, sampleTrainee2);
        trainerStorage.put(1L, sampleTrainer1);
        trainingDAO.create(sampleTraining1);
        trainingDAO.create(sampleTraining2);
        val allTrainings = trainingDAO.findAll();
        assertEquals(2, allTrainings.size());
        assertEquals(1L, allTrainings.get(0).getTrainingId());
        assertEquals(2L, allTrainings.get(1).getTrainingId());
    }

    @Test
    public void testDeleteTraining() {
        traineeStorage.put(1L, sampleTrainee1);
        trainerStorage.put(2L, sampleTrainer2);
        trainingDAO.create(sampleTraining1);
        val id = sampleTraining1.getTrainingId();

        trainingDAO.delete(id);
        assertFalse(trainingDAO.findById(id).isPresent());
    }

    @Test
    public void testFindByIdNonExistent() {
        val training = trainingDAO.findById(999L);
        assertFalse(training.isPresent());
    }
}
