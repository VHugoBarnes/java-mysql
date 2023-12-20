package dev.victorvazquez;

import dev.victorvazquez.model.Employee;
import dev.victorvazquez.repository.EmployeeRepository;
import dev.victorvazquez.repository.Repository;
import dev.victorvazquez.util.DatabaseConnection;

import java.sql.*;


public class Main {
    public static void main(String[] args) throws SQLException {
        try(Connection myConn = DatabaseConnection.getInstance();) {
            Repository<Employee> repository = new EmployeeRepository();

            repository.findAll().forEach(System.out::println);
            System.out.println("============================");
            System.out.println(repository.getById(1));;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Something went wrong");
        }
    }
}