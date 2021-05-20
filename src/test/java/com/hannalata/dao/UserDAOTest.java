package com.hannalata.dao;

import com.hannalata.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserDAOTest {

    @Test
    void save() {
        User user =
                new User("test", "testPassword", "testName", "testLastName", "testEmail", "testPhone");
        UserDAO.save(user);
        assertNotNull(user.getId());
    }

    @Test
    void delete() {
        User user = new User();
        UserDAO.delete(3);
    }
}