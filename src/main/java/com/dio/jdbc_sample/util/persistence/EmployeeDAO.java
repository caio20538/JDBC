package com.dio.jdbc_sample.util.persistence;

import com.dio.jdbc_sample.util.persistence.Entity.EmployeeEntity;
import com.mysql.cj.jdbc.StatementImpl;

import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static java.time.ZoneOffset.UTC;

public class EmployeeDAO {

    public void insert(final EmployeeEntity entity) {
        String sql = "INSERT INTO employees (name, salary, birthday) VALUES (?, ?, ?)";

        try (var connection = ConnectionUtil.getConnection();
             var statement = connection.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, entity.getName());
            statement.setBigDecimal(2, entity.getSalary());
            statement.setString(3, formatOffSetDateTime(entity.getBirthday()));

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


    public void update(final EmployeeEntity entity){
        try(var connection = ConnectionUtil.getConnection();
            var statement = connection.createStatement()
        ){
            var sql = "UPDATE employees set " +
                    "name = '" + entity.getName() + "'," +
                    "salary =" + entity.getSalary().toString() + "," +
                    "birthday ='" + formatOffSetDateTime(entity.getBirthday()) + "'" +
                    "WHERE id =" + entity.getId();

            if (statement instanceof StatementImpl impl)
                entity.setId(impl.getLastInsertID());
            statement.executeUpdate(sql);
            System.out.printf("Foram afetados %s registros na base de dados", statement.getUpdateCount());


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(final long id){
        try(var connection = ConnectionUtil.getConnection();
            var statement = connection.createStatement()
        ){
            var sql = "DELETE FROM employees WHERE id =" + id;
            statement.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<EmployeeEntity> findAll(){
        List<EmployeeEntity> entities = new ArrayList<>();

        try(var connection = ConnectionUtil.getConnection();
            var statement = connection.createStatement()
        ){
           statement.executeQuery("SELECT * FROM employees ORDER BY name");

           var result = statement.getResultSet();
           while (result.next()){
                var entity = new EmployeeEntity();
                entity.setId(result.getLong("id"));
                entity.setName(result.getString("name"));
                entity.setSalary(result.getBigDecimal("salary"));
                var birthdayInstant = result.getTimestamp("birthday").toInstant();
                var birthday = OffsetDateTime.ofInstant(birthdayInstant, UTC);
                entity.setBirthday(birthday);
                entities.add(entity);

           }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entities;
    }

    public EmployeeEntity findById(final long id){
        var entity = new EmployeeEntity();

        try(var connection = ConnectionUtil.getConnection();
            var statement = connection.createStatement()
        ){
            statement.executeQuery("SELECT * FROM employees WHERE id = " + id);

            var result = statement.getResultSet();
            if (result.next()){
                entity.setId(result.getLong("id"));
                entity.setName(result.getString("name"));
                entity.setSalary(result.getBigDecimal("salary"));
                var birthdayInstant = result.getTimestamp("birthday").toInstant();
                var birthday = OffsetDateTime.ofInstant(birthdayInstant, UTC);
                entity.setBirthday(birthday);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entity;
    }

    private String formatOffSetDateTime(final OffsetDateTime offsetDateTime){
        var utcDateTime = offsetDateTime.withOffsetSameInstant(UTC);
        return utcDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

}
