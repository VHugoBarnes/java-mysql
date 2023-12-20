package dev.victorvazquez;

import dev.victorvazquez.model.Employee;
import dev.victorvazquez.repository.EmployeeRepository;
import dev.victorvazquez.repository.Repository;
import dev.victorvazquez.util.DatabaseConnection;

import java.sql.*;


public class Main {
    public static void main(String[] args) throws SQLException {
        try {
            Repository<Employee> repository = new EmployeeRepository();

            //* Save new employee
            Employee nicoleEmployee = new Employee();
            nicoleEmployee.setSalary(3_000_000f);
            nicoleEmployee.setEmail("nicole@gmail.com");
            nicoleEmployee.setFirst_name("Nicole");
            nicoleEmployee.setPa_surname("Rodriguez");
            nicoleEmployee.setMa_surname("Gonzalez");


            repository.save(nicoleEmployee);

            //* Find all employees
            repository.findAll().forEach(System.out::println);
            System.out.println("============================");

            //* Delete employee
            repository.delete(1);

            //* Find one employee
            System.out.println(repository.getById(1));


        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Something went wrong");
        }
    }
}