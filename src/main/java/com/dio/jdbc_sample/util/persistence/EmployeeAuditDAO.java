package com.dio.jdbc_sample.util.persistence;

import com.dio.jdbc_sample.util.persistence.Entity.EmployeeAuditEntity;
import com.dio.jdbc_sample.util.persistence.Entity.EmployeeEntity;
import com.dio.jdbc_sample.util.persistence.Entity.OperationEnum;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.time.ZoneOffset.UTC;
import static java.util.Objects.isNull;

public class EmployeeAuditDAO {

    public List<EmployeeAuditEntity> findAll(){
        List<EmployeeAuditEntity> entities = new ArrayList<>();

        try(var connection = ConnectionUtil.getConnection();
            var statement = connection.createStatement()
        ){
            statement.executeQuery("SELECT * FROM view_employee_audit");

            var result = statement.getResultSet();

            while (result.next()){
                entities.add(new EmployeeAuditEntity(
                        result.getLong("employee_id"),
                        result.getString("name"),
                        result.getString("old_name"),
                        result.getBigDecimal("salary"),
                        result.getBigDecimal("old_salary"),
                        getDateTimeOrNull(result, "birthday"),
                        getDateTimeOrNull(result, "old_birthday"),
                        OperationEnum.getByDbOperation(result.getString("operation"))
                ));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entities;
    }

    public OffsetDateTime getDateTimeOrNull(final ResultSet result, final String field) throws SQLException {
        return isNull(result.getTimestamp(field)) ? null :
                OffsetDateTime.ofInstant(result.getTimestamp(field).toInstant(), UTC);
    }
}
