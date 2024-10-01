package service;

import com.example.gymcrm.dao.core.TraineeDAO;
import com.example.gymcrm.dao.core.TrainerDAO;
import com.example.gymcrm.dao.core.TrainingDAO;
import com.example.gymcrm.model.Trainee;
import com.example.gymcrm.model.Trainer;
import com.example.gymcrm.model.Training;
import com.example.gymcrm.model.criteria.TrainingSearchCriteria;
import com.example.gymcrm.service.core.TrainingService;
import com.example.gymcrm.service.impl.TrainingServiceImpl;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Calendar;
import java.util.Date;
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
    private Training sampleTraining3;

    @BeforeEach
    public void setUp() {
        trainingDAO = Mockito.mock(TrainingDAO.class);
        traineeDAO = Mockito.mock(TraineeDAO.class);
        trainerDAO = Mockito.mock(TrainerDAO.class);
        trainingService = new TrainingServiceImpl(trainingDAO, traineeDAO, trainerDAO);

        sampleTraining1 = mock(Training.class);
        when(sampleTraining1.getId()).thenReturn(1L);
        when(sampleTraining1.getTraineeId()).thenReturn(1L);
        when(sampleTraining1.getTrainerId()).thenReturn(1L);
        sampleTraining2 = mock(Training.class);
        when(sampleTraining2.getId()).thenReturn(2L);
        when(sampleTraining2.getTraineeId()).thenReturn(2L);
        when(sampleTraining2.getTrainerId()).thenReturn(2L);
        sampleTraining3 = mock(Training.class);
        when(sampleTraining3.getId()).thenReturn(3L);
        when(sampleTraining3.getTraineeId()).thenReturn(3L);
        when(sampleTraining3.getTrainerId()).thenReturn(3L);
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


    @Test
    public void testGetTrainingsMatchingCriteria() {
        when(trainingDAO.findAll()).thenReturn(List.of(sampleTraining1, sampleTraining2, sampleTraining3));
        when(sampleTraining1.getTraineeUsername()).thenReturn("username2");
        when(sampleTraining1.getTrainingType()).thenReturn("type 2");
        when(sampleTraining2.getTraineeUsername()).thenReturn("username1");
        when(sampleTraining2.getTrainingType()).thenReturn("type 1");
        when(sampleTraining3.getTraineeUsername()).thenReturn("username3");
        when(sampleTraining3.getTrainingType()).thenReturn("type 1");

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        val dateNow = calendar.getTime();
        calendar.add(Calendar.DAY_OF_MONTH, 5);
        val futureDate = new Date(calendar.getTime().getTime());
        calendar.add(Calendar.DAY_OF_MONTH, -15);
        val pastDate = new Date(calendar.getTime().getTime());

        when(sampleTraining1.getTrainingDate()).thenReturn(pastDate);
        when(sampleTraining2.getTrainingDate()).thenReturn(dateNow);
        when(sampleTraining3.getTrainingDate()).thenReturn(futureDate);

        val criteria = TrainingSearchCriteria.builder().traineeUsername("username1").trainingType("type 1").dateFrom(pastDate).dateTo(futureDate).build();

        val result = trainingService.getTrainingsMatchingCriteria(criteria);
        assertEquals(1, result.size());
        assertEquals(sampleTraining2, result.get(0));
    }

}
