package com.hannalata.dao;

import com.hannalata.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {

    public static Order save(Order order) {
        String sql = "INSERT INTO orders (item_id, cart_id, amount) " +
                "VALUES (?, ?, ?)";
        String sequenceSQL = "SELECT currval(pg_get_serial_sequence('orders','id'))";
        int result = 0;
        try (Connection connection = ConnectionToDB.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(sql);
             PreparedStatement sequenceStatement =
                     connection.prepareStatement(sequenceSQL)) {
            preparedStatement.setInt(1, order.getItem().getId());
            preparedStatement.setInt(2, order.getCart().getId());
            preparedStatement.setLong(3, order.getAmount());

            result = preparedStatement.executeUpdate();

            if (result == 1) {
                ResultSet resultSet = sequenceStatement.executeQuery();
                while (resultSet.next()) {
                    Integer id = resultSet.getInt(1);
                    order.setId(id);
                    break;
                }
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }


    public static Order getById(Integer id) {
        String sql = "SELECT * FROM orders WHERE id = ?";
        try (Connection connection = ConnectionToDB.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Order order = new Order (
                        resultSet.getInt("id"),
                        null,
                        null,
                        resultSet.getInt("amount")
                );
                Item item = ItemDAO.getById(resultSet.getInt("item_id"));
                order.setItem(item);
                Cart cart = CartDAO.getById(resultSet.getInt("cart_id"));
                order.setCart(cart);
                return order;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Order> getAllByCart(Cart cart) {
        String sql = "SELECT * FROM orders WHERE cart_id = ?";
        List<Order> orders = new ArrayList<>();
        try (Connection connection = ConnectionToDB.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, cart.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Order order = new Order (
                        resultSet.getInt("id"),
                        null,
                        cart,
                        resultSet.getInt("amount")
                );
                Item item = ItemDAO.getById(resultSet.getInt("item_id"));
                order.setItem(item);
                orders.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orders;
    }


    public static Order update(Order order) {
        String sql =
                "UPDATE order SET item_id = ?, amount = ? WHERE id = ?";

        try (Connection connection = ConnectionToDB.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, order.getItem().getId());
            preparedStatement.setLong(2, order.getAmount());
            preparedStatement.setInt(3, order.getId());
            int result = preparedStatement.executeUpdate();
            if(result == 1) {
                return order;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void delete(Integer id) {
        String sql = "DELETE FROM orders WHERE id = ?";
        try (Connection connection = ConnectionToDB.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
