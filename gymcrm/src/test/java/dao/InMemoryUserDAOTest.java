package dao;

import com.example.gymcrm.dao.inmemory.InMemoryUserDAO;
import com.example.gymcrm.model.User;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.jupiter.api.Assertions.*;

public class InMemoryUserDAOTest {

    private InMemoryUserDAO userDAO;
    private Map<Long, User> userStorage;

    private User sampleUser1;
    private User sampleUser2;

    @BeforeEach
    public void setup() {
        userStorage = new ConcurrentHashMap<>();
        userDAO = new InMemoryUserDAO(userStorage);

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
    public void testCreateUser() {
        userDAO.create(sampleUser1);
        assertNotNull(sampleUser1.getId());
        assertEquals(1, userStorage.size());
    }

    @Test
    public void testFindById() {
        userDAO.create(sampleUser1);
        val foundUser = userDAO.findById(sampleUser1.getId());
        assertTrue(foundUser.isPresent());
        assertEquals(1L, foundUser.get().getId());
    }

    @Test
    public void testFindAll() {
        userDAO.create(sampleUser1);
        userDAO.create(sampleUser2);
        val allUsers = userDAO.findAll();
        assertEquals(2, allUsers.size());
        assertEquals(1L, allUsers.get(0).getId());
        assertEquals(2L, allUsers.get(1).getId());
    }

    @Test
    public void testUpdateUser() {
        userDAO.create(sampleUser1);
        sampleUser1.setFirstName("Jack");
        userDAO.update(sampleUser1);

        val updatedUser = userDAO.findById(sampleUser1.getId());
        assertTrue(updatedUser.isPresent());
        assertEquals("Jack", updatedUser.get().getFirstName());
    }

    @Test
    public void testDeleteUser() {
        userDAO.create(sampleUser1);
        val id = sampleUser1.getId();

        userDAO.delete(id);
        assertFalse(userDAO.findById(id).isPresent());
    }

    @Test
    public void testFindByIdNonExistent() {
        val user = userDAO.findById(999L);
        assertFalse(user.isPresent());
    }
}
