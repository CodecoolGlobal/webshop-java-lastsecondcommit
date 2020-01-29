package com.codecool.shop.dao.implementation.JDBC;

import com.codecool.shop.config.MyConfig;

import javax.security.auth.login.Configuration;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCHelper {

    private static final MyConfig cfg = MyConfig.getInstance();
    private static final String user = cfg.getProperty("user");
    private static final String password = cfg.getProperty("password");
    private static final String url = cfg.getProperty("url");
    private static final String database = cfg.getProperty("database");

    private static final String DATABASE_NAME = database;
    private static final String DATABASE_URL = "jdbc:postgresql://" + url + "/" + database;
    private static final String DB_USER = user;
    private static final String DB_PASSWORD = password;
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
