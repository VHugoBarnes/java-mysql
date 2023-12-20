package dev.victorvazquez.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static String url = "jdbc:mysql://localhost:3306/project";
    private static String user = "root";
    private static String pwd = "";
    private static Connection cnn;

    public static Connection getInstance() throws SQLException {
        if(cnn == null) {
            cnn = DriverManager.getConnection(url, user, pwd);
        }

        return cnn;
    }
}
