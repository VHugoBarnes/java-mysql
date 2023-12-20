package dev.victorvazquez.repository;

import dev.victorvazquez.model.Employee;
import dev.victorvazquez.util.DatabaseConnection;
import dev.victorvazquez.util.DatabasePoolConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository implements Repository<Employee>{
    private Connection getConnection() throws SQLException {
        return DatabasePoolConnection.getConnection();
    }

    @Override
    public List<Employee> findAll() throws SQLException {
        List<Employee> employees = new ArrayList<>();

        try(    Connection cnn = getConnection();
                Statement myStamt = cnn.createStatement();
            ResultSet myRes = myStamt.executeQuery("SELECT * FROM employees")){
            while (myRes.next()) {
                employees.add(createEmployee(myRes));
            }
        }

        return employees;
    }

    @Override
    public Employee getById(Integer id) throws SQLException {
        Employee employee = null;

        try(Connection cnn = getConnection(); PreparedStatement myStamt = cnn.prepareStatement("SELECT * FROM employees WHERE id=?;")){
            myStamt.setInt(1,id);
            try(ResultSet myRes = myStamt.executeQuery()) {
                if(myRes.next()) {
                    employee = createEmployee(myRes);
                }
            }
        }

        return employee;
    }

    @Override
    public void save(Employee employee) throws SQLException {
        employee.setId(null);

        try(Connection cnn = getConnection(); PreparedStatement myStatement = cnn.prepareStatement("INSERT INTO employees (first_name,pa_surname,ma_surname,email,salary) VALUES(?,?,?,?,?);")) {
            myStatement.setString(1, employee.getFirst_name());
            myStatement.setString(2, employee.getPa_surname());
            myStatement.setString(3, employee.getMa_surname());
            myStatement.setString(4, employee.getEmail());
            myStatement.setFloat(5, employee.getSalary());

            int rowsAffected = myStatement.executeUpdate();

            if(rowsAffected > 0) {
                return;
            } else {
                throw new SQLException("[sql-error]");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("[sql-error]");
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        try(Connection cnn = getConnection(); PreparedStatement myStatement = cnn.prepareStatement("DELETE FROM employees WHERE id=?;")){
            myStatement.setInt(1, id);

            int rowsAffected = myStatement.executeUpdate();

            if(rowsAffected > 0) {
                return;
            } else {
                System.out.println("Employee with id:"+id+" not found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("[sql-error]");
        }
    }

    private static Employee createEmployee(ResultSet myRes) throws SQLException {
        Employee e = new Employee();

        e.setId(myRes.getInt("id"));
        e.setFirst_name(myRes.getString("first_name"));
        e.setPa_surname(myRes.getString("pa_surname"));
        e.setMa_surname(myRes.getString("ma_surname"));
        e.setEmail(myRes.getString("email"));
        e.setSalary(myRes.getFloat("salary"));

        return e;
    }
}
