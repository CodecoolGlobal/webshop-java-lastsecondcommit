package com.codecool.shop.dao.implementation.JDBC;

import java.sql.Connection;

public class JDBC {
    protected Connection connection = JDBCHelper.getConnection();
}
