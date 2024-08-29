package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    public static Connection getConnection() {
        final String URL = "jdbc:mysql://localhost:3306/newdb";
        final String USERNAME = "root";
        final String PASSWORD = "root";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");


            return DriverManager.getConnection(URL, USERNAME, PASSWORD);


        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
