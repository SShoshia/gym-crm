package facade;

import com.example.gymcrm.facade.GymCRMFacade;
import com.example.gymcrm.facade.GymCRMFacadeImpl;
import com.example.gymcrm.model.Trainee;
import com.example.gymcrm.model.Trainer;
import com.example.gymcrm.model.Training;
import com.example.gymcrm.model.User;
import com.example.gymcrm.service.core.TrainerService;
import com.example.gymcrm.service.core.TraineeService;
import com.example.gymcrm.service.core.TrainingService;
import com.example.gymcrm.service.core.UserService;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class GymCRMFacadeImplTest {

    private UserService userService;
    private TrainerService trainerService;
    private TraineeService traineeService;
    private TrainingService trainingService;
    private GymCRMFacade gymFacade;

    @BeforeEach
    public void setUp() {
        userService = mock(UserService.class);
        trainerService = mock(TrainerService.class);
        traineeService = mock(TraineeService.class);
        trainingService = mock(TrainingService.class);
        gymFacade = new GymCRMFacadeImpl(userService, traineeService, trainerService, trainingService);
    }

    @Test
    public void testCreateUser() {
        User user = new User();
        gymFacade.createUser(user);
        verify(userService, times(1)).createUser(user);
    }

    @Test
    public void testGetUser() {
        User user = new User();
        when(userService.getUser(1L)).thenReturn(Optional.of(user));

        val result = gymFacade.getUser(1L);
        assertTrue(result.isPresent());
        verify(userService, times(1)).getUser(1L);
    }

    @Test
    public void testGetAllUsers() {
        when(userService.getAllUsers()).thenReturn(Arrays.asList(new User(), new User()));

        assertEquals(2, gymFacade.getAllUsers().size());
        verify(userService, times(1)).getAllUsers();
    }


    @Test
    public void testUpdateUser() {
        User user = new User();
        gymFacade.updateUser(user);
        verify(userService, times(1)).updateUser(user);
    }

    @Test
    public void testDeleteUser() {
        gymFacade.deleteUser(1L);
        verify(userService, times(1)).deleteUser(1L);
    }

    @Test
    public void testCreateTrainee() {
        Trainee trainee = new Trainee();
        gymFacade.createTrainee(trainee);
        verify(traineeService, times(1)).createTrainee(trainee);
    }

    @Test
    public void testGetTrainee() {
        Trainee trainee = new Trainee();
        when(traineeService.getTrainee(1L)).thenReturn(Optional.of(trainee));

        val result = gymFacade.getTrainee(1L);
        assertTrue(result.isPresent());
        verify(traineeService, times(1)).getTrainee(1L);
    }

    @Test
    public void testGetAllTrainees() {
        when(traineeService.getAllTrainees()).thenReturn(Arrays.asList(new Trainee(), new Trainee()));

        assertEquals(2, gymFacade.getAllTrainees().size());
        verify(traineeService, times(1)).getAllTrainees();
    }


    @Test
    public void testUpdateTrainee() {
        Trainee trainee = new Trainee();
        gymFacade.updateTrainee(trainee);
        verify(traineeService, times(1)).updateTrainee(trainee);
    }

    @Test
    public void testDeleteTrainee() {
        gymFacade.deleteTrainee(1L);
        verify(traineeService, times(1)).deleteTrainee(1L);
    }

    @Test
    public void testCreateTrainer() {
        Trainer trainer = new Trainer();
        gymFacade.createTrainer(trainer);
        verify(trainerService, times(1)).createTrainer(trainer);
    }

    @Test
    public void testGetTrainer() {
        Trainer trainer = new Trainer();
        when(trainerService.getTrainer(1L)).thenReturn(Optional.of(trainer));

        val result = gymFacade.getTrainer(1L);
        assertTrue(result.isPresent());
        verify(trainerService, times(1)).getTrainer(1L);
    }

    @Test
    public void testGetAllTrainers() {
        when(trainerService.getAllTrainers()).thenReturn(Arrays.asList(new Trainer(), new Trainer()));

        assertEquals(2, gymFacade.getAllTrainers().size());
        verify(trainerService, times(1)).getAllTrainers();
    }

    @Test
    public void testUpdateTrainer() {
        Trainer trainer = new Trainer();
        gymFacade.updateTrainer(trainer);
        verify(trainerService, times(1)).updateTrainer(trainer);
    }

    @Test
    public void testDeleteTrainer() {
        gymFacade.deleteTrainer(1L);
        verify(trainerService, times(1)).deleteTrainer(1L);
    }

    @Test
    public void testCreateTraining() {
        Training training = new Training();
        gymFacade.createTraining(training);
        verify(trainingService, times(1)).createTraining(training);
    }

    @Test
    public void testGetTraining() {
        Training training = new Training();
        when(trainingService.getTraining(1L)).thenReturn(Optional.of(training));

        val result = gymFacade.getTraining(1L);
        assertTrue(result.isPresent());
        verify(trainingService, times(1)).getTraining(1L);
    }

    @Test
    public void testGetAllTrainings() {
        when(trainingService.getAllTrainings()).thenReturn(Arrays.asList(new Training(), new Training()));

        assertEquals(2, gymFacade.getAllTrainings().size());
        verify(trainingService, times(1)).getAllTrainings();
    }

}
