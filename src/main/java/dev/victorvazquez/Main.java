package dev.victorvazquez;

import dev.victorvazquez.util.DatabaseConnection;

import java.sql.*;


public class Main {
    public static void main(String[] args) throws SQLException {
        try(Connection myConn = DatabaseConnection.getInstance();
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