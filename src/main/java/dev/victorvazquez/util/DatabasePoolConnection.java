package dev.victorvazquez.util;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabasePoolConnection {
    private static String url = "jdbc:mysql://localhost:3306/project";
    private static String user = "root";
    private static String pwd = "";
    private static BasicDataSource pool;

    private static BasicDataSource getInstance() throws SQLException {
        if(pool == null) {
            pool =  new BasicDataSource();
            pool.setUrl(url);
            pool.setUsername(user);
            pool.setPassword(pwd);

            pool.setInitialSize(3);
            pool.setMinIdle(2);
            pool.setMaxIdle(10);
            pool.setMaxTotal(10);
        }

        return pool;
    }

    public static Connection getConnection() throws SQLException {
        return getInstance().getConnection();
    }
}
