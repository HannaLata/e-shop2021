package com.hannalata.dao;

import com.hannalata.model.Item;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ItemDAOTest {

    private static List<Item> items;

    @BeforeAll
    static void setPreConditions() {
        items = new ArrayList<>();
    }

    @Test
    void save() {
        Item item = new Item("testCode",  "testName",
                1000, 1);
        ItemDAO.save(item);
        items.add(item);
        assertNotNull(item.getId());
    }

    @Test
    void update() {
        Item item = new Item("testCode",  "testName",
                1000, 1);
        Item savedItem = ItemDAO.save(item);
        items.add(savedItem);
        assertNotNull(savedItem);
        assertNotNull(savedItem.getId());
        assertEquals("testCode", savedItem.getCode());

        item.setCode("newCode");

        Item updateItem = ItemDAO.update(item);
        assertNotNull(updateItem);
        assertEquals("newCode", updateItem.getCode());
    }

    @Test
    void getAndDelete() {
        Item item = new Item("testCode",  "testName",
                1000, 1);
        ItemDAO.save(item);
        items.add(item);
        assertNotNull(item.getId());
        Item targetItem = ItemDAO.getById(item.getId());
        assertNotNull(targetItem);
        ItemDAO.delete(targetItem.getId());
        targetItem = ItemDAO.getById(item.getId());
        assertNull(targetItem);
    }

    @AfterAll
    static void deleteTestData() {
        items.forEach(it -> ItemDAO.delete(it.getId()));
    }
}