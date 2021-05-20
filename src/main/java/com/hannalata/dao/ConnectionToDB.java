package com.hannalata.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

class ConnectionToDB {

    private static final Logger LOG = Logger.getLogger(ConnectionToDB.class.getName());

    private static final String URL = "jdbc:postgresql://localhost:5432/e_shop";
    private static final String USER = "postgres";
    private static final String PASSWORD = "N1i2k3o4l5a6y7!";

    static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            LOG.severe("Connection to db denied!");
        }
        return connection;
    }
}
