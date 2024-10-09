package facade;

import com.example.gymcrm.facade.GymCRMFacade;
import com.example.gymcrm.facade.GymCRMFacadeImpl;
import com.example.gymcrm.model.criteria.TrainerSearchCriteria;
import com.example.gymcrm.model.criteria.TrainingSearchCriteria;
import com.example.gymcrm.model.entity.Trainee;
import com.example.gymcrm.model.entity.Trainer;
import com.example.gymcrm.model.entity.Training;
import com.example.gymcrm.model.entity.User;
import com.example.gymcrm.service.core.TraineeService;
import com.example.gymcrm.service.core.TrainerService;
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
        verify(userService).createUser(user);
    }

    @Test
    public void testGetUser() {
        User user = new User();
        when(userService.getUser(1L)).thenReturn(Optional.of(user));

        val result = gymFacade.getUser(1L);
        assertTrue(result.isPresent());
        verify(userService).getUser(1L);
    }

    @Test
    public void testGetAllUsers() {
        when(userService.getAllUsers()).thenReturn(Arrays.asList(new User(), new User()));

        assertEquals(2, gymFacade.getAllUsers().size());
        verify(userService).getAllUsers();
    }


    @Test
    public void testUpdateUser() {
        User user = new User();
        gymFacade.updateUser(user);
        verify(userService).updateUser(user);
    }

    @Test
    public void testDeleteUser() {
        gymFacade.deleteUser(1L);
        verify(userService).deleteUser(1L);
    }

    @Test
    public void testCreateTrainee() throws Exception {
        Trainee trainee = new Trainee();
        gymFacade.createTrainee(trainee);
        verify(traineeService).createTrainee(trainee);
    }

    @Test
    public void testGetTrainee() {
        Trainee trainee = new Trainee();
        when(traineeService.getTrainee(1L)).thenReturn(Optional.of(trainee));

        val result = gymFacade.getTrainee(1L);
        assertTrue(result.isPresent());
        verify(traineeService).getTrainee(1L);
    }

    @Test
    public void testGetAllTrainees() {
        when(traineeService.getAllTrainees()).thenReturn(Arrays.asList(new Trainee(), new Trainee()));

        assertEquals(2, gymFacade.getAllTrainees().size());
        verify(traineeService).getAllTrainees();
    }


    @Test
    public void testUpdateTrainee() throws Exception {
        Trainee trainee = new Trainee();
        gymFacade.updateTrainee(trainee);
        verify(traineeService).updateTrainee(trainee);
    }

    @Test
    public void testDeleteTrainee() throws Exception {
        val trainee = new Trainee();
        gymFacade.deleteTrainee(trainee);
        verify(traineeService).deleteTrainee(trainee);
    }

    @Test
    public void testCreateTrainer() throws Exception {
        Trainer trainer = new Trainer();
        gymFacade.createTrainer(trainer);
        verify(trainerService).createTrainer(trainer);
    }

    @Test
    public void testGetTrainer() {
        Trainer trainer = new Trainer();
        when(trainerService.getTrainer(1L)).thenReturn(Optional.of(trainer));

        val result = gymFacade.getTrainer(1L);
        assertTrue(result.isPresent());
        verify(trainerService).getTrainer(1L);
    }

    @Test
    public void testGetAllTrainers() {
        when(trainerService.getAllTrainers()).thenReturn(Arrays.asList(new Trainer(), new Trainer()));

        assertEquals(2, gymFacade.getAllTrainers().size());
        verify(trainerService).getAllTrainers();
    }

    @Test
    public void testUpdateTrainer() throws Exception {
        Trainer trainer = new Trainer();
        gymFacade.updateTrainer(trainer);
        verify(trainerService).updateTrainer(trainer);
    }

    @Test
    public void testDeleteTrainer() throws Exception {
        val trainer = new Trainer();
        gymFacade.deleteTrainer(trainer);
        verify(trainerService).deleteTrainer(trainer);
    }

    @Test
    public void testCreateTraining() {
        Training training = new Training();
        gymFacade.createTraining(training);
        verify(trainingService).createTraining(training);
    }

    @Test
    public void testGetTraining() {
        Training training = new Training();
        when(trainingService.getTraining(1L)).thenReturn(Optional.of(training));

        val result = gymFacade.getTraining(1L);
        assertTrue(result.isPresent());
        verify(trainingService).getTraining(1L);
    }

    @Test
    public void testGetAllTrainings() {
        when(trainingService.getAllTrainings()).thenReturn(Arrays.asList(new Training(), new Training()));

        assertEquals(2, gymFacade.getAllTrainings().size());
        verify(trainingService).getAllTrainings();
    }

    @Test
    public void testGetTrainingsMatchingCriteria() {
        when(trainingService.getTrainingsMatchingCriteria(any())).thenReturn(Arrays.asList(new Training(), new Training()));

        val criteria = new TrainingSearchCriteria();
        assertEquals(2, gymFacade.getTrainingsMatchingCriteria(criteria).size());
        verify(trainingService).getTrainingsMatchingCriteria(criteria);
    }

    @Test
    public void testGetTrainersMatchingCriteria() {
        when(trainerService.getTrainersMatchingCriteria(any())).thenReturn(Arrays.asList(new Trainer(), new Trainer()));

        val criteria = new TrainerSearchCriteria();
        assertEquals(2, gymFacade.getTrainersMatchingCriteria(criteria).size());
        verify(trainerService).getTrainersMatchingCriteria(criteria);

    }

    @Test
    public void testGetTrainerByUsername() {
        val trainer = Optional.of(new Trainer());
        when(trainerService.getTrainerByUsername("username")).thenReturn(trainer);

        assertEquals(trainer, gymFacade.getTrainerByUsername("username"));
        verify(trainerService).getTrainerByUsername("username");
    }

    @Test
    public void testGetTraineeByUsername() {
        val trainee = Optional.of(new Trainee());
        when(traineeService.getTraineeByUsername("username")).thenReturn(trainee);

        assertEquals(trainee, gymFacade.getTraineeByUsername("username"));
        verify(traineeService).getTraineeByUsername("username");
    }

    @Test
    public void testDeleteTraineeByUsername() {
        gymFacade.deleteTraineeByUsername("username");
        verify(traineeService).deleteTraineeByUsername("username");
    }

}
