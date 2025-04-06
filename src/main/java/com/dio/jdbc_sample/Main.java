package com.dio.jdbc_sample;

import com.dio.jdbc_sample.util.persistence.*;
import com.dio.jdbc_sample.util.persistence.Entity.ContactEntity;
import com.dio.jdbc_sample.util.persistence.Entity.EmployeeEntity;
import net.datafaker.Faker;
import org.flywaydb.core.Flyway;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.Locale;
import java.util.stream.Stream;

import static java.time.ZoneOffset.UTC;


public class Main {

    private final static EmployeeParamDAO employeeDAO = new EmployeeParamDAO();
    private final static EmployeeAuditDAO employeeAuditDAO = new EmployeeAuditDAO();
    private final static Faker faker = new Faker(new Locale("pt", "BR"));
    private final static ContactDAO contactDAO = new ContactDAO();

    public static void main(String[] args) {
//        try(var connection = ConnectionUtil.getConnection()){
//            System.out.println("Conectou!");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        var flyway = Flyway.configure()
                .dataSource("jdbc:mysql://localhost:3306/jdbcsample", "root", "!")
                .load();
        flyway.migrate();

//        var employee = new EmployeeEntity();

//        employee.setName("pedro");
//        employee.setSalary(new BigDecimal("4000.00"));
//        employee.setBirthday(OffsetDateTime.now().minusYears(24));
//
//        System.out.println(employee);
//        employeeDAO.insert(employee);
//        System.out.println(employee);
//
//        var employee2 = new EmployeeEntity();
//        employee2.setName("caio");
//        employee2.setSalary(new BigDecimal("7000.00"));
//        employee2.setBirthday(OffsetDateTime.now().minusYears(21));
//
//        System.out.println(employee2);
//        employeeDAO.insert(employee2);
//        System.out.println(employee2);
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

//        var employeeUpdate = new EmployeeEntity();
//        employeeUpdate.setId(employee2.getId());
//        employeeUpdate.setName("Petherson'");
//        employeeUpdate.setSalary(new BigDecimal("2000.00"));
//        employeeUpdate.setBirthday(OffsetDateTime.now().minusYears(21));
////
//        employeeDAO.update(employeeUpdate);
//
//        employeeDAO.delete(employee4.getId());
//
//        employeeAuditDAO.findAll().forEach(System.out::println);
//        var entities = Stream.generate(() -> {
//            var employees = new EmployeeEntity();
//            employees.setName(faker.name().fullName());
//            employees.setSalary(new BigDecimal(faker.number().digits(4)));
//
//            int idade = faker.number().numberBetween(20, 41); // Gera uma idade entre 20 e 40 anos
//            LocalDate dataNascimento = LocalDate.now().minusYears(idade);
//            OffsetDateTime dataNascimentoOffset = OffsetDateTime.of(dataNascimento, LocalTime.MIN, UTC);
//
//            employees.setBirthday(dataNascimentoOffset);
//            return employees;
//        }).limit(10000).toList();
//
//        employeeDAO.insertBath(entities);

//        var employee = new EmployeeEntity();
//
//        employee.setName("pedro");
//        employee.setSalary(new BigDecimal("4000.00"));
//        employee.setBirthday(OffsetDateTime.now().minusYears(24));
//
//        System.out.println(employee);
//        employeeDAO.insert(employee);
//        System.out.println(employee);
//
//        var contact = new ContactEntity();
////
//        contact.setDescription("pedro@pedro.com");
//        contact.setType("e-mail");
//        contact.setEmployee(employee);
//        contactDAO.insert(contact);

        System.out.println(employeeDAO.findById(2));

//        employeeDAO.delete(1);

    }
}
