package com.hannalata.dao;

import com.hannalata.model.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderDAOTest {

    private static final long CURRENT_TIME = new Date().getTime();
    private static List<Order> orders;
    private static List<Item> items;
    private static List<Cart> carts;
    private static List<User> users;


    @BeforeAll
    static void setPreConditions() {

        items = new ArrayList<>();
        carts = new ArrayList<>();
        orders = new ArrayList<>();
        users = new ArrayList<>();
    }

    @Test
    void save() {
        User user = new User("testLogin", "testPassword", "testName",
                "testLastName", "testEmail", "testPhone");
        UserDAO.save(user);
        users.add(user);
        assertNotNull(user.getId());
        Item item = new Item("testCode",  "testName",
                1000, 1);
        ItemDAO.save(item);
        items.add(item);
        assertNotNull(item.getId());
        Cart cart = new Cart(Status.OPEN, user, CURRENT_TIME);
        CartDAO.save(cart);
        carts.add(cart);
        assertNotNull(cart.getId());
        Order order = new Order(item, cart, 5);
        OrderDAO.save(order);
        orders.add(order);
        assertNotNull(order.getId());
    }

    @Test
    void getById() {
        User user = new User("testLogin", "testPassword", "testName",
                "testLastName", "testEmail", "testPhone");
        UserDAO.save(user);
        users.add(user);
        assertNotNull(user.getId());
        Item item = new Item("testCode",  "testName",
                1000, 1);
        ItemDAO.save(item);
        items.add(item);
        assertNotNull(item.getId());
        Cart cart = new Cart(Status.OPEN, user, CURRENT_TIME);
        CartDAO.save(cart);
        carts.add(cart);
        assertNotNull(cart.getId());
        Order order = new Order(item, cart, 5);
        OrderDAO.save(order);
        orders.add(order);
        assertNotNull(order.getId());

        Order savedOrder = OrderDAO.getById(order.getId());
        assertNotNull(savedOrder);
        assertNotNull(savedOrder.getItem());
        assertNotNull(savedOrder.getItem().getId());
        assertNotNull(savedOrder.getCart());
        assertNotNull(savedOrder.getCart().getId());



    }

    @Test
    void update() {
        User user = new User("testLogin", "testPassword", "testName",
                "testLastName", "testEmail", "testPhone");
        UserDAO.save(user);
        users.add(user);
        assertNotNull(user.getId());
        Item item = new Item("testCode",  "testName",
                1000, 1);
        ItemDAO.save(item);
        items.add(item);
        assertNotNull(item.getId());
        Cart cart = new Cart(Status.OPEN, user, CURRENT_TIME);
        CartDAO.save(cart);
        carts.add(cart);
        assertNotNull(cart.getId());

        Order order = new Order(item, cart, 5);
        Order savedOrder = OrderDAO.save(order);
        orders.add(savedOrder);
        assertNotNull(savedOrder);
        assertNotNull(savedOrder.getId());
        assertEquals(5, savedOrder.getAmount());

        order.setAmount(10);

        Order updateOrder = OrderDAO.update(order);
        assertNotNull(updateOrder.getId());
        assertEquals(10, updateOrder.getAmount());
    }

    @Test
    void getAndDelete() {
        User user = new User("testLogin", "testPassword", "testName",
                "testLastName", "testEmail", "testPhone");
        UserDAO.save(user);
        users.add(user);
        assertNotNull(user.getId());
        Item item = new Item("testCode",  "testName",
                1000, 1);
        ItemDAO.save(item);
        items.add(item);
        assertNotNull(item.getId());
        Cart cart = new Cart(Status.OPEN, user, CURRENT_TIME);
        CartDAO.save(cart);
        carts.add(cart);
        assertNotNull(cart.getId());
        Order order = new Order(item, cart, 5);
        OrderDAO.save(order);
        orders.add(order);
        assertNotNull(order.getId());

        Order targetOrder = OrderDAO.getById(order.getId());
        assertNotNull(targetOrder);
        OrderDAO.delete(targetOrder.getId());
        targetOrder = OrderDAO.getById(order.getId());
        assertNull(targetOrder);
    }

    @AfterAll
    static void deleteTestData() {

        orders.forEach(it -> OrderDAO.delete(it.getId()));
        carts.forEach(it -> CartDAO.delete(it.getId()));
        users.forEach(it -> UserDAO.delete(it.getId()));
        items.forEach(it -> ItemDAO.delete(it.getId()));

    }

}