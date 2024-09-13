package service;

import com.example.gymcrm.dao.core.TraineeDAO;
import com.example.gymcrm.dao.core.UserDAO;
import com.example.gymcrm.model.Trainee;
import com.example.gymcrm.model.User;
import com.example.gymcrm.service.core.TraineeService;
import com.example.gymcrm.service.impl.TraineeServiceImpl;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TraineeServiceImplTest {

    private TraineeDAO traineeDAO;
    private UserDAO userDAO;
    private TraineeService traineeService;

    private Trainee sampleTrainee1;
    private Trainee sampleTrainee2;

    @BeforeEach
    public void setUp() {
        traineeDAO = Mockito.mock(TraineeDAO.class);
        userDAO = Mockito.mock(UserDAO.class);
        traineeService = new TraineeServiceImpl(traineeDAO, userDAO);

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
    public void testCreateTraineeCallsDaoMethodOnArgument() {
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
    public void testUpdateTraineeCallsDaoMethodOnArgument() {
        traineeService.updateTrainee(sampleTrainee1);
        verify(traineeDAO, times(1)).update(sampleTrainee1);
    }

    @Test
    public void testDeleteTraineeCallsDaoMethodOnArgument() {
        traineeService.deleteTrainee(1L);
        verify(traineeDAO, times(1)).delete(1L);
    }

}
