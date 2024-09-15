package service;

import com.example.gymcrm.dao.core.TraineeDAO;
import com.example.gymcrm.dao.core.TrainerDAO;
import com.example.gymcrm.dao.core.TrainingDAO;
import com.example.gymcrm.model.Trainee;
import com.example.gymcrm.model.Trainer;
import com.example.gymcrm.model.Training;
import com.example.gymcrm.service.core.TrainingService;
import com.example.gymcrm.service.impl.TrainingServiceImpl;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class TrainingServiceImplTest {

    private TrainingDAO trainingDAO;
    private TraineeDAO traineeDAO;
    private TrainerDAO trainerDAO;
    private TrainingService trainingService;

    private Training sampleTraining1;
    private Training sampleTraining2;

    @BeforeEach
    public void setUp() {
        trainingDAO = Mockito.mock(TrainingDAO.class);
        traineeDAO = Mockito.mock(TraineeDAO.class);
        trainerDAO = Mockito.mock(TrainerDAO.class);
        trainingService = new TrainingServiceImpl(trainingDAO, traineeDAO, trainerDAO);
        sampleTraining1 = new Training();
        sampleTraining1.setId(1L);
        sampleTraining1.setTraineeId(1L);
        sampleTraining1.setTrainerId(1L);
        sampleTraining2 = new Training();
        sampleTraining2.setId(2L);
        sampleTraining2.setTraineeId(2L);
        sampleTraining2.setTrainerId(2L);
    }

    @Test
    public void testCreateTrainingCallsDaoMethodOnArgument() {
        when(traineeDAO.findById(sampleTraining1.getTraineeId())).thenReturn(Optional.of(new Trainee()));
        when(trainerDAO.findById(sampleTraining1.getTrainerId())).thenReturn(Optional.of(new Trainer()));
        trainingService.createTraining(sampleTraining1);
        verify(trainingDAO, times(1)).create(sampleTraining1);
    }

    @Test
    public void testGetTrainingReturnsSameAsDaoMethod() {
        when(trainingDAO.findById(1L)).thenReturn(Optional.of(sampleTraining1));

        val result = trainingService.getTraining(1L);

        assertTrue(result.isPresent());
        assertEquals(sampleTraining1, result.get());
    }

    @Test
    public void testGetAllTrainingsReturnsSameAsDaoMethod() {
        when(trainingDAO.findAll()).thenReturn(List.of(sampleTraining1, sampleTraining2));

        val result = trainingService.getAllTrainings();

        assertEquals(2, result.size());
        assertEquals(sampleTraining1, result.get(0));
        assertEquals(sampleTraining2, result.get(1));
    }

}
