package dev.victorvazquez;

import java.sql.*;


public class Main {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/project";
        String user = "root";
        String pwd = "";

        try(Connection myConn = DriverManager.getConnection(url, user, pwd);
            Statement myStamt = myConn.createStatement();
            ResultSet myRes = myStamt.executeQuery("SELECT * FROM employees");) {
            while(myRes.next()) {
                System.out.println(myRes.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Something went wrong");
        }
    }
}