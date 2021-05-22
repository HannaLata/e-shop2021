package com.hannalata.dao;

import com.hannalata.model.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class UserDAOTest {

    private static List<User> users;

    @BeforeAll
    static void setPreConditions() {
        users = new ArrayList<>();
    }

    @Test
    void save() {
        User user = new User("testLogin", "testPassword", "testName",
                        "testLastName", "testEmail", "testPhone");
        UserDAO.save(user);
        users.add(user);
        assertNotNull(user.getId());
    }

    @Test
    void update() {
        User user = new User("testLogin", "testPassword", "testName",
                        "testLastName", "testEmail", "testPhone");
        User savedUser = UserDAO.save(user);
        users.add(savedUser);
        assertNotNull(savedUser);
        assertNotNull(savedUser.getId());
        assertEquals("testPassword", savedUser.getPassword());

        user.setPassword("newPassword");

        User updateUser = UserDAO.update(user);
        assertNotNull(updateUser);
        assertEquals("newPassword", updateUser.getPassword());
    }

    @Test
    void getAndDelete() {
            User user = new User("testLogin", "testPassword", "testName",
                    "testLastName", "testEmail", "testPhone");
            UserDAO.save(user);
            users.add(user);
            assertNotNull(user.getId());
            User targetUser = UserDAO.getById(user.getId());
            assertNotNull(targetUser);
            UserDAO.delete(targetUser.getId());
            targetUser = UserDAO.getById(user.getId());
            assertNull(targetUser);
    }

    @AfterAll
    static void deleteTestData() {

        users.forEach(it -> UserDAO.delete(it.getId()));
    }
}