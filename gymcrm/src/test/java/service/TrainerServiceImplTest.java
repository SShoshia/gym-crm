package service;

import com.example.gymcrm.auth.Authenticator;
import com.example.gymcrm.dao.core.TrainerDAO;
import com.example.gymcrm.dao.core.UserDAO;
import com.example.gymcrm.model.Trainer;
import com.example.gymcrm.model.Training;
import com.example.gymcrm.model.User;
import com.example.gymcrm.model.criteria.TrainerSearchCriteria;
import com.example.gymcrm.service.core.TrainerService;
import com.example.gymcrm.service.impl.TrainerServiceImpl;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TrainerServiceImplTest {

    private TrainerDAO trainerDAO;
    private UserDAO userDAO;
    private TrainerService trainerService;
    private Authenticator authenticator;

    private Trainer sampleTrainer1;
    private Trainer sampleTrainer2;

    @BeforeEach
    public void setUp() {
        trainerDAO = Mockito.mock(TrainerDAO.class);
        userDAO = Mockito.mock(UserDAO.class);
        authenticator = Mockito.mock(Authenticator.class);
        trainerService = new TrainerServiceImpl(trainerDAO, userDAO, authenticator);

        sampleTrainer1 = mock(Trainer.class);
        when(sampleTrainer1.getId()).thenReturn(1L);
        when(sampleTrainer1.getUserId()).thenReturn(1L);
        when(sampleTrainer1.getUser()).thenReturn(new User());
        when(sampleTrainer1.getSpecialization()).thenReturn("spec 1");
        sampleTrainer2 = mock(Trainer.class);
        when(sampleTrainer2.getId()).thenReturn(2L);
        when(sampleTrainer2.getUserId()).thenReturn(2L);
        when(sampleTrainer2.getUser()).thenReturn(new User());
        when(sampleTrainer2.getSpecialization()).thenReturn("spec 2");

        when(authenticator.authenticate(any())).thenReturn(true);
    }

    @Test
    public void testCreateTrainerCallsDaoMethodOnArgument() throws Exception {
        when(userDAO.findById(sampleTrainer1.getUserId())).thenReturn(Optional.of(new User()));
        trainerService.createTrainer(sampleTrainer1);
        verify(trainerDAO, times(1)).create(sampleTrainer1);
    }

    @Test
    public void testCreateTrainerWithNoUser() {
        when(userDAO.findById(sampleTrainer1.getUserId())).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> trainerService.createTrainer(sampleTrainer1));
    }

    @Test
    public void testGetTrainerReturnsSameAsDaoMethod() {
        when(trainerDAO.findById(1L)).thenReturn(Optional.of(sampleTrainer1));

        val result = trainerService.getTrainer(1L);

        assertTrue(result.isPresent());
        assertEquals(sampleTrainer1, result.get());
    }

    @Test
    public void testGetAllTrainersReturnsSameAsDaoMethod() {
        when(trainerDAO.findAll()).thenReturn(List.of(sampleTrainer1, sampleTrainer2));

        val result = trainerService.getAllTrainers();

        assertEquals(2, result.size());
        assertEquals(sampleTrainer1, result.get(0));
        assertEquals(sampleTrainer2, result.get(1));
    }

    @Test
    public void testUpdateTrainerCallsDaoMethodOnArgument() throws Exception {
        when(userDAO.findById(sampleTrainer1.getUserId())).thenReturn(Optional.of(new User()));
        when(trainerDAO.findById(sampleTrainer1.getId())).thenReturn(Optional.of(sampleTrainer1));
        trainerService.createTrainer(sampleTrainer1);

        trainerService.updateTrainer(sampleTrainer1);
        verify(trainerDAO, times(1)).update(sampleTrainer1);
    }

    @Test
    public void testDeleteTrainerCallsDaoMethodOnArgument() throws Exception {
        trainerService.deleteTrainer(sampleTrainer1);
        verify(trainerDAO, times(1)).delete(sampleTrainer1.getId());
    }

    @Test
    public void testNoUpdateOnUnAuthenticatedUser() {
        when(authenticator.authenticate(any())).thenReturn(false);
        assertThrows(RuntimeException.class, () -> trainerService.updateTrainer(sampleTrainer1));
    }

    @Test
    public void testGetTrainersMatchingCriteria() {
        when(trainerDAO.findAll()).thenReturn(List.of(sampleTrainer1, sampleTrainer2));

        val sampleTraining1 = mock(Training.class);
        when(sampleTraining1.getTraineeUsername()).thenReturn("username1");
        val sampleTraining2 = mock(Training.class);
        when(sampleTraining2.getTraineeUsername()).thenReturn("username2");
        val sampleTraining3 = mock(Training.class);
        when(sampleTraining3.getTraineeUsername()).thenReturn("username3");

        when(sampleTrainer1.getTrainings()).thenReturn(List.of(sampleTraining1, sampleTraining2));
        when(sampleTrainer2.getTrainings()).thenReturn(List.of(sampleTraining2, sampleTraining3));

        val result = trainerService.getTrainersMatchingCriteria(TrainerSearchCriteria.builder().traineeUsernameNotAssigned("username1").build());

        assertEquals(1, result.size());
        assertEquals(sampleTrainer2, result.get(0));
    }

    @Test
    public void testGetTrainerByUsername() {
        when(trainerDAO.findAll()).thenReturn(List.of(sampleTrainer1, sampleTrainer2));

        when(sampleTrainer1.getUsername()).thenReturn("username1");
        when(sampleTrainer2.getUsername()).thenReturn("username2");

        val result = trainerService.getTrainerByUsername(sampleTrainer1.getUsername());
        assertTrue(result.isPresent());
        assertEquals(sampleTrainer1, result.get());
    }

}
