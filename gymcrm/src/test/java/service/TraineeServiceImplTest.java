package service;

import com.example.gymcrm.auth.Authenticator;
import com.example.gymcrm.dao.core.TraineeDAO;
import com.example.gymcrm.dao.core.UserDAO;
import com.example.gymcrm.model.entity.Trainee;
import com.example.gymcrm.model.entity.User;
import com.example.gymcrm.service.core.TraineeService;
import com.example.gymcrm.service.impl.TraineeServiceImpl;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TraineeServiceImplTest {

    private TraineeDAO traineeDAO;
    private UserDAO userDAO;
    private TraineeService traineeService;
    private Authenticator authenticator;

    private Trainee sampleTrainee1;
    private Trainee sampleTrainee2;

    @BeforeEach
    public void setUp() {
        traineeDAO = Mockito.mock(TraineeDAO.class);
        userDAO = Mockito.mock(UserDAO.class);
        authenticator = Mockito.mock(Authenticator.class);
        traineeService = new TraineeServiceImpl(traineeDAO, userDAO, authenticator);

        sampleTrainee1 = mock();
        when(sampleTrainee1.getId()).thenReturn(1L);
        when(sampleTrainee1.getUserId()).thenReturn(1L);
        when(sampleTrainee1.getAddress()).thenReturn("1 example street");
        when(sampleTrainee1.getDateOfBirth()).thenReturn(new Date());
        when(sampleTrainee1.getUser()).thenReturn(new User());
        sampleTrainee2 = mock();
        when(sampleTrainee2.getId()).thenReturn(2L);
        when(sampleTrainee2.getUserId()).thenReturn(2L);
        when(sampleTrainee2.getAddress()).thenReturn("2 example street");
        when(sampleTrainee2.getDateOfBirth()).thenReturn(new Date());
        when(sampleTrainee2.getUser()).thenReturn(new User());

        when(authenticator.authenticate(any())).thenReturn(true);
    }

    @Test
    public void testCreateTraineeCallsDaoMethodOnArgument() throws Exception {
        when(userDAO.findById(sampleTrainee1.getUserId())).thenReturn(Optional.of(new User()));
        traineeService.createTrainee(sampleTrainee1);
        verify(traineeDAO, times(1)).create(sampleTrainee1);
    }

    @Test
    public void testCreateTraineeWithNoUser() {
        when(userDAO.findById(sampleTrainee1.getUserId())).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> traineeService.createTrainee(sampleTrainee1));
    }

    @Test
    public void testGetTraineeReturnsSameAsDaoMethod() {
        when(traineeDAO.findById(1L)).thenReturn(Optional.of(sampleTrainee1));

        val result = traineeService.getTrainee(1L);

        assertTrue(result.isPresent());
        assertEquals(sampleTrainee1, result.get());
    }

    @Test
    public void testGetAllTraineesReturnsSameAsDaoMethod() {
        when(traineeDAO.findAll()).thenReturn(List.of(sampleTrainee1, sampleTrainee2));

        val result = traineeService.getAllTrainees();

        assertEquals(2, result.size());
        assertEquals(sampleTrainee1, result.get(0));
        assertEquals(sampleTrainee2, result.get(1));
    }

    @Test
    public void testUpdateTraineeCallsDaoMethodOnArgument() throws Exception {
        when(userDAO.findById(sampleTrainee1.getUserId())).thenReturn(Optional.of(new User()));
        when(traineeDAO.findById(sampleTrainee1.getId())).thenReturn(Optional.of(sampleTrainee1));
        traineeService.createTrainee(sampleTrainee1);

        traineeService.updateTrainee(sampleTrainee1);
        verify(traineeDAO, times(1)).update(sampleTrainee1);
    }

    @Test
    public void testDeleteTraineeCallsDaoMethodOnArgument() throws Exception {
        when(traineeDAO.findById(1L)).thenReturn(Optional.of(sampleTrainee1));
        traineeService.deleteTrainee(sampleTrainee1);
        verify(traineeDAO, times(1)).delete(1L);
    }

    @Test
    public void testNoUpdateOnUnAuthenticatedUser() {
        when(authenticator.authenticate(any())).thenReturn(false);
        assertThrows(RuntimeException.class, () -> traineeService.updateTrainee(sampleTrainee1));
    }

    @Test
    public void testGetTraineeByUsername() {
        when(traineeDAO.findAll()).thenReturn(List.of(sampleTrainee1, sampleTrainee2));

        when(sampleTrainee1.getUsername()).thenReturn("username1");
        when(sampleTrainee2.getUsername()).thenReturn("username2");

        val result = traineeService.getTraineeByUsername(sampleTrainee1.getUsername());
        assertTrue(result.isPresent());
        assertEquals(sampleTrainee1, result.get());
    }

    @Test
    public void testDeleteTraineeByUsername() {
        when(traineeDAO.findAll()).thenReturn(List.of(sampleTrainee1, sampleTrainee2));
        when(sampleTrainee1.getUsername()).thenReturn("username1");
        when(sampleTrainee2.getUsername()).thenReturn("username2");

        traineeService.deleteTraineeByUsername(sampleTrainee1.getUsername());
        verify(traineeDAO, times(1)).delete(sampleTrainee1.getId());
    }

}
