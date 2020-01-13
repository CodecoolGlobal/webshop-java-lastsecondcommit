package com.codecool.shop.dao.implementation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCHelper {

    private static final String DATABASE_NAME = System.getenv("PSQL_DB_NAME");
    private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432" + DATABASE_NAME;
    private static final String DB_USER = System.getenv("PSQL_USER");
    private static final String DB_PASSWORD = System.getenv("PSQL_PASSWORD");
    private static Connection connection;

    private static Connection generateConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(
                    DATABASE_URL,
                    DB_USER,
                    DB_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static Connection getConnection() {
        if (connection == null) {
            connection =  generateConnection();
        }
        return connection;
    }

}
