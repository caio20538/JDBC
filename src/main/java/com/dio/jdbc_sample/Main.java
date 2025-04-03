package com.dio.jdbc_sample;

import com.dio.jdbc_sample.util.persistence.ConnectionUtil;
import com.dio.jdbc_sample.util.persistence.EmployeeAuditDAO;
import com.dio.jdbc_sample.util.persistence.EmployeeDAO;
import com.dio.jdbc_sample.util.persistence.EmployeeParamDAO;
import com.dio.jdbc_sample.util.persistence.Entity.EmployeeEntity;
import org.flywaydb.core.Flyway;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.OffsetDateTime;


public class Main {

    private final static EmployeeParamDAO employeeDAO = new EmployeeParamDAO();
    private final static EmployeeAuditDAO employeeAuditDAO = new EmployeeAuditDAO();

    public static void main(String[] args) {
//        try(var connection = ConnectionUtil.getConnection()){
//            System.out.println("Conectou!");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        var flyway = Flyway.configure()
                .dataSource("jdbc:mysql://localhost:3306/jdbcsample", "root", "")
                .load();
        flyway.migrate();

        var employee = new EmployeeEntity();

//        employee.setName("pedro");
//        employee.setSalary(new BigDecimal("4000.00"));
//        employee.setBirthday(OffsetDateTime.now().minusYears(24));
//
//        System.out.println(employee);
//        employeeDAO.insert(employee);
//        System.out.println(employee);

        var employee2 = new EmployeeEntity();
        employee2.setName("caio");
        employee2.setSalary(new BigDecimal("7000.00"));
        employee2.setBirthday(OffsetDateTime.now().minusYears(21));

        System.out.println(employee2);
        employeeDAO.insert(employee2);
        System.out.println(employee2);
//
//        var employee3 = new EmployeeEntity();
//        employee3.setName("Carlos");
//        employee3.setSalary(new BigDecimal("3000.00"));
//        employee3.setBirthday(OffsetDateTime.now().minusYears(26));
//
//        System.out.println(employee3);
//        employeeDAO.insert(employee3);
//        System.out.println(employee3);
//
//        var employee4 = new EmployeeEntity();
//        employee4.setName("Jão");
//        employee4.setSalary(new BigDecimal("2000.00"));
//        employee4.setBirthday(OffsetDateTime.now().minusYears(26));
//
//        System.out.println(employee4);
//        employeeDAO.insert(employee4);
//        System.out.println(employee4);

       // employeeDAO.findAll().forEach(System.out::println);

        //System.out.println(employeeDAO.findById(1));

        var employeeUpdate = new EmployeeEntity();
        employeeUpdate.setId(employee2.getId());
        employeeUpdate.setName("Petherson'");
        employeeUpdate.setSalary(new BigDecimal("2000.00"));
        employeeUpdate.setBirthday(OffsetDateTime.now().minusYears(21));
//
        employeeDAO.update(employeeUpdate);
//
//        employeeDAO.delete(employee4.getId());

       // employeeAuditDAO.findAll().forEach(System.out::println);

    }
}
