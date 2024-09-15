package service;

import com.example.gymcrm.dao.core.TrainerDAO;
import com.example.gymcrm.dao.core.UserDAO;
import com.example.gymcrm.model.Trainer;
import com.example.gymcrm.model.User;
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

    private Trainer sampleTrainer1;
    private Trainer sampleTrainer2;

    @BeforeEach
    public void setUp() {
        trainerDAO = Mockito.mock(TrainerDAO.class);
        userDAO = Mockito.mock(UserDAO.class);
        trainerService = new TrainerServiceImpl(trainerDAO, userDAO);

        sampleTrainer1 = mock(Trainer.class);
        when(sampleTrainer1.getId()).thenReturn(1L);
        when(sampleTrainer1.getUserId()).thenReturn(1L);
        when(sampleTrainer1.getSpecialization()).thenReturn("spec 1");
        sampleTrainer2 = mock(Trainer.class);
        when(sampleTrainer2.getId()).thenReturn(2L);
        when(sampleTrainer2.getUserId()).thenReturn(2L);
        when(sampleTrainer2.getSpecialization()).thenReturn("spec 2");
    }

    @Test
    public void testCreateTrainerCallsDaoMethodOnArgument() {
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
    public void testUpdateTrainerCallsDaoMethodOnArgument() {
        when(userDAO.findById(sampleTrainer1.getUserId())).thenReturn(Optional.of(new User()));
        when(trainerDAO.findById(sampleTrainer1.getId())).thenReturn(Optional.of(sampleTrainer1));
        trainerService.createTrainer(sampleTrainer1);

        trainerService.updateTrainer(sampleTrainer1);
        verify(trainerDAO, times(1)).update(sampleTrainer1);
    }

    @Test
    public void testDeleteTrainerCallsDaoMethodOnArgument() {
        trainerService.deleteTrainer(1L);
        verify(trainerDAO, times(1)).delete(1L);
    }

}
