package com.thogakade.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private static Connection connection;
    private static DbConnection dbConnection;


    private DbConnection() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakade", "root", "1234");
    }

    public static DbConnection getInstance() throws SQLException {
        if (dbConnection == null) {
            return dbConnection = new DbConnection();
        } else {
            return dbConnection;
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
