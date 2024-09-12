package com.mango.trycrud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class mysqlConnector {

    public static Connection connection;

    public static void connect() throws SQLException, ClassNotFoundException {
        String url = "jdbc:mysql://localhost:3306/tryCrud";
        String user = "root";
        String pass = "password";
        connection = DriverManager.getConnection(url, user, pass);
    }
}
