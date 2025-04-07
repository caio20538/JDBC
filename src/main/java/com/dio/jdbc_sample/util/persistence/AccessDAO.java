package com.dio.jdbc_sample.util.persistence;

import com.dio.jdbc_sample.util.persistence.Entity.EmployeeEntity;

import java.sql.SQLException;
import java.sql.Timestamp;

import static java.time.ZoneOffset.UTC;

public class AccessDAO {

    public void insert(final long employeeId, final  long moduleId) {
        String sql = "INSERT INTO accesses (id_employee,id_module) VALUES (?, ?)";

        try (var connection = ConnectionUtil.getConnection();
             var statement = connection.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS)) {

            statement.setLong(1, employeeId);
            statement.setLong(2, moduleId);

            int affectedRows = statement.executeUpdate();
            System.out.printf("Foram afetados %s registros na base de dados%n", affectedRows);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
