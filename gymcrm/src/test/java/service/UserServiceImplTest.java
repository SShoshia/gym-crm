package service;

import com.example.gymcrm.dao.core.UserDAO;
import com.example.gymcrm.model.entity.User;
import com.example.gymcrm.service.core.UserService;
import com.example.gymcrm.service.impl.UserServiceImpl;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {

    private UserDAO userDAO;
    private UserService userService;

    private User sampleUser1;
    private User sampleUser2;

    @BeforeEach
    public void setUp() {
        userDAO = Mockito.mock(UserDAO.class);
        userService = new UserServiceImpl(userDAO);

        sampleUser1 = new User();
        sampleUser1.setId(1L);
        sampleUser1.setFirstName("John");
        sampleUser1.setLastName("Doe");
        sampleUser2 = new User();
        sampleUser2.setId(2L);
        sampleUser2.setFirstName("Jane");
        sampleUser2.setLastName("Doe");
    }

    @Test
    public void testCreateUserCallsDaoMethodOnArgument() {
        userService.createUser(sampleUser1);
        verify(userDAO, times(1)).create(sampleUser1);
    }

    @Test
    public void testGetUserReturnsSameAsDaoMethod() {
        when(userDAO.findById(1L)).thenReturn(Optional.of(sampleUser1));

        val result = userService.getUser(1L);

        assertTrue(result.isPresent());
        assertEquals(sampleUser1, result.get());
    }

    @Test
    public void testGetAllUsersReturnsSameAsDaoMethod() {
        when(userDAO.findAll()).thenReturn(List.of(sampleUser1, sampleUser2));

        val result = userService.getAllUsers();

        assertEquals(2, result.size());
        assertEquals(sampleUser1, result.get(0));
        assertEquals(sampleUser2, result.get(1));
    }

    @Test
    public void testUpdateUserCallsDaoMethodOnArgument() {
        when(userDAO.findById(sampleUser1.getId())).thenReturn(Optional.of(sampleUser1));

        userService.updateUser(sampleUser1);
        verify(userDAO, times(1)).update(sampleUser1);
    }

    @Test
    public void testDeleteUserCallsDaoMethodOnArgument() {
        when(userDAO.findById(sampleUser1.getId())).thenReturn(Optional.of(sampleUser1));

        userService.deleteUser(1L);
        verify(userDAO, times(1)).delete(1L);
    }

    @Test
    public void testCreateUserSetsCorrectUsernameAndPassword() {
        userService.createUser(sampleUser1);
        assertEquals(sampleUser1.getFirstName() + "." + sampleUser1.getLastName(), sampleUser1.getUsername());
        assertEquals(10, sampleUser1.getPassword().length());
    }
}
