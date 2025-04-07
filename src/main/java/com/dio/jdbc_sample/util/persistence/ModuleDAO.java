package com.dio.jdbc_sample.util.persistence;

import com.dio.jdbc_sample.util.persistence.Entity.ContactEntity;
import com.dio.jdbc_sample.util.persistence.Entity.EmployeeEntity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ModuleDAO {

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

    public List<ContactEntity> findByEmployeeId(final long employeeId) {
        List<ContactEntity> entities = new ArrayList<>();

        try (var connection = ConnectionUtil.getConnection();
             var statement = connection.prepareStatement("SELECT * FROM modules m" +
                     " INNER JOIN accesses a " +
                     "ON a.id_module = m.id " +
                     "INNER JOIN employees e " +
                     "ON e.id = a.id_employee")
        ) {
            statement.setLong(1, employeeId);
            var result = statement.executeQuery();

            while (result.next()) {
                var entity = new ContactEntity();
                entity.setId(result.getLong("id"));
                entity.setDescription(result.getString("description"));
                entity.setType(result.getString("type"));
                entity.setEmployee(new EmployeeEntity());
                entity.getEmployee().setId(result.getLong("employee_id"));
                entities.add(entity);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entities;
    }
}
