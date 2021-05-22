package com.hannalata.dao;

import com.hannalata.model.Cart;
import com.hannalata.model.Status;
import com.hannalata.model.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CartDAOTest {

    private static final long CURRENT_TIME = new Date().getTime();
    private static List<Cart> carts;
    private static List<User> users;

    @BeforeAll
    static void setPreConditions() {

        carts = new ArrayList<>();
        users = new ArrayList<>();
    }

    @Test
    void save() {
        User user = new User("testLogin", "testPassword", "testName",
                "testLastName", "testEmail", "testPhone");
        UserDAO.save(user);
        users.add(user);
        assertNotNull(user.getId());
        Cart cart = new Cart(Status.OPEN, user, CURRENT_TIME);
        CartDAO.save(cart);
        carts.add(cart);
        assertNotNull(cart.getId());
    }

    @Test
    void getById() {
        User user = new User("testLogin", "testPassword", "testName",
                "testLastName", "testEmail", "testPhone");
        UserDAO.save(user);
        users.add(user);
        assertNotNull(user.getId());
        Cart cart = new Cart(Status.OPEN, user, CURRENT_TIME);
        CartDAO.save(cart);
        carts.add(cart);
        assertNotNull(cart.getId());

        Cart savedCart = CartDAO.getById(cart.getId());
        assertNotNull(savedCart);
        assertNotNull(savedCart.getUser());
        assertNotNull(savedCart.getUser().getId());


    }

    @Test
    void updateStatus() {
        User user = new User("testLogin", "testPassword", "testName",
                "testLastName", "testEmail", "testPhone");
        UserDAO.save(user);
        users.add(user);
        assertNotNull(user.getId());
        Cart cart = new Cart(Status.OPEN, user, CURRENT_TIME);
        Cart savedCart = CartDAO.save(cart);
        carts.add(savedCart);
        assertNotNull(savedCart);
        assertNotNull(savedCart.getId());
        assertEquals(Status.OPEN, savedCart.getStatus());

        cart.setStatus(Status.CLOSED);

        Cart updateCart = CartDAO.updateStatus(cart, cart.getStatus());
        assertNotNull(updateCart);
        assertEquals(Status.CLOSED, updateCart.getStatus());
    }

    @Test
    void getAndDelete() {
        User user = new User("testLogin", "testPassword", "testName",
                "testLastName", "testEmail", "testPhone");
        UserDAO.save(user);
        users.add(user);
        assertNotNull(user.getId());
        Cart cart = new Cart(Status.OPEN, user, CURRENT_TIME);
        CartDAO.save(cart);
        assertNotNull(cart.getId());
        Cart targetCart = CartDAO.getById(cart.getId());
        assertNotNull(targetCart);
        CartDAO.delete(targetCart.getId());
        targetCart = CartDAO.getById(cart.getId());
        assertNull(targetCart);
    }

    @AfterAll
    static void deleteTestData() {

        carts.forEach(it -> CartDAO.delete(it.getId()));
        users.forEach(it -> UserDAO.delete(it.getId()));
    }


}