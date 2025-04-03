package com.dio.jdbc_sample;

import com.dio.jdbc_sample.util.persistence.ConnectionUtil;
import com.dio.jdbc_sample.util.persistence.EmployeeDAO;
import com.dio.jdbc_sample.util.persistence.Entity.EmployeeEntity;
import org.flywaydb.core.Flyway;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.OffsetDateTime;


public class Main {

    private final static EmployeeDAO employeeDAO = new EmployeeDAO();

    public static void main(String[] args) {
//        try(var connection = ConnectionUtil.getConnection()){
//            System.out.println("Conectou!");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        var flyway = Flyway.configure()
                .dataSource("jdbc:mysql://localhost:3306/jdbcsample", "", "")
                .load();
        flyway.migrate();

//        var employee = new EmployeeEntity();
//
//        employee.setName("pedro");
//        employee.setSalary(new BigDecimal("4000.00"));
//        employee.setBirthday(OffsetDateTime.now().minusYears(24));
//
//        employeeDAO.insert(employee);
//
//        System.out.println(employee);

       // employeeDAO.findAll().forEach(System.out::println);

        //System.out.println(employeeDAO.findById(1));

        /*var employee = new EmployeeEntity();
        employee.setId(2);
        employee.setName("Golias");
        employee.setSalary(new BigDecimal("500.00"));
        employee.setBirthday(OffsetDateTime.now().minusYears(28));

        employeeDAO.update(employee);*/

        //employeeDAO.delete(4);

    }
}
