package com.dio.jdbc_sample.util.persistence;

import com.dio.jdbc_sample.util.persistence.Entity.ContactEntity;
import com.dio.jdbc_sample.util.persistence.Entity.EmployeeEntity;

import java.sql.SQLException;
import java.sql.Timestamp;

import static java.time.ZoneOffset.UTC;

public class ContactDAO {

    public void insert(final ContactEntity entity) {
        String sql = "INSERT INTO contacts (description, type, employee_id) VALUES (?, ?, ?)";

        try (var connection = ConnectionUtil.getConnection();
             var statement = connection.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, entity.getDescription());
            statement.setString(2, entity.getType());
            statement.setLong(3, entity.getEmployee().getId());

            int affectedRows = statement.executeUpdate();
            System.out.printf("Foram afetados %s registros na base de dados%n", affectedRows);

            // Pega o ID gerado automaticamente
            try (var generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    entity.setId(generatedKeys.getLong(1)); // Agora o ID está correto!
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
