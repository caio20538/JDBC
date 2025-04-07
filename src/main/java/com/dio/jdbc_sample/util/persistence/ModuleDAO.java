package com.dio.jdbc_sample.util.persistence;

import com.dio.jdbc_sample.util.persistence.Entity.ContactEntity;
import com.dio.jdbc_sample.util.persistence.Entity.EmployeeEntity;
import com.dio.jdbc_sample.util.persistence.Entity.ModuleEntity;

import java.sql.SQLException;
import java.sql.Statement;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.time.ZoneOffset.UTC;

public class ModuleDAO {

    /// TODO :
    public void insert(ModuleEntity module) {
        String sql = "INSERT INTO modules (name) VALUES (?)";

        try (var connection = ConnectionUtil.getConnection();
             var statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, module.getName());
            statement.executeUpdate();

            try (var generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    module.setId(generatedKeys.getLong(1));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public List<ModuleEntity> findAll() {
        List<ModuleEntity> entities = new ArrayList<>();
        var sql = "SELECT m.id module_id, " +
                "m.name module_name, " +
                "e.id employee_id, "+
                "e.name employee_name, " +
                "e.salary, " +
                "e.birthday" +
                "  FROM modules m" +
                " INNER JOIN accesses a " +
                "ON a.id_module = m.id " +
                "INNER JOIN employees e " +
                "ON e.id = a.id_employee";

        try (var connection = ConnectionUtil.getConnection();
             var statement = connection.prepareStatement(sql)
        ) {
            var result = statement.executeQuery();
            var hasNext = true;

            while (hasNext) {
                ModuleEntity module = new ModuleEntity();
                module.setId(result.getLong("module_id"));
                module.setName(result.getString("module_name"));
                module.setEmployeeEntities(new ArrayList<>());

                do {
                    var employee = new EmployeeEntity();
                    employee.setId(result.getLong("employee_id"));
                    employee.setName(result.getString("employee_name"));
                    employee.setSalary(result.getBigDecimal("salary"));
                    var birthday = result.getTimestamp("birthday").toInstant();
                    employee.setBirthday(OffsetDateTime.ofInstant(birthday, UTC));
                    module.getEmployeeEntities().add(employee);
                    hasNext = result.next();
                } while ((hasNext) && (module.getId() == result.getLong("module_id")));
                entities.add(module);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entities;
    }
}
