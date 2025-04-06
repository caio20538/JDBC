package com.dio.jdbc_sample.util.persistence;

import com.dio.jdbc_sample.util.persistence.Entity.ContactEntity;
import com.dio.jdbc_sample.util.persistence.Entity.EmployeeEntity;
import com.mysql.cj.jdbc.StatementImpl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static java.time.ZoneOffset.UTC;
import static java.util.TimeZone.LONG;

public class EmployeeParamDAO {

    public void insert(final EmployeeEntity entity) {
        String sql = "INSERT INTO employees (name, salary, birthday) VALUES (?, ?, ?)";

        try (var connection = ConnectionUtil.getConnection();
             var statement = connection.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, entity.getName());
            statement.setBigDecimal(2, entity.getSalary());
            statement.setTimestamp(3,
                    Timestamp.valueOf(entity.getBirthday().atZoneSimilarLocal(UTC).toLocalDateTime())
            );

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

    public void insertWithProcedure(final EmployeeEntity entity) {
        String sql = "call prc_insert_employee(?, ?, ?, ?)";

        try (var connection = ConnectionUtil.getConnection();
             var statement = connection.prepareCall(sql)) {

            statement.registerOutParameter(1, LONG);
            statement.setString(2, entity.getName());
            statement.setBigDecimal(3, entity.getSalary());
            statement.setTimestamp(4,
                    Timestamp.valueOf(entity.getBirthday().atZoneSimilarLocal(UTC).toLocalDateTime())
            );

            statement.execute();
            entity.setId(statement.getLong(1));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertBath(final List<EmployeeEntity> entities) {
        String sql = "INSERT INTO employees (name, salary, birthday) VALUES (?, ?, ?)";

        try (var connection = ConnectionUtil.getConnection()) {
            try (var statement = connection.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS)){
                connection.setAutoCommit(false);

                //dessa  forma eu quebro em lotes e tenho mais controle dos commits

                //para que serve?
                // transações e evitar duplicação de transação
                for (int i = 0; i < entities.size(); i++) {
                    statement.setString(1, entities.get(i).getName());
                    statement.setBigDecimal(2, entities.get(i).getSalary());
                    statement.setTimestamp(3,
                            Timestamp.valueOf(entities.get(i).getBirthday().atZoneSimilarLocal(UTC).toLocalDateTime())
                    );
                    statement.addBatch();

                    if (i % 1000 == 0 || i == entities.size() -1)
                        statement.executeBatch();

                    // simular uma excessão if (i == 8000) throw new SQLException();
                }
                //para executar de fato precisa disso
                connection.commit();
            }catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(final EmployeeEntity entity){
        String sql = "UPDATE employees set name = ?, salary = ?, birthday = ? WHERE id = ?";
        try (var connection = ConnectionUtil.getConnection();
             var statement = connection.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, entity.getName());
            statement.setBigDecimal(2, entity.getSalary());
            statement.setTimestamp(3,
                    Timestamp.valueOf(entity.getBirthday().atZoneSimilarLocal(UTC).toLocalDateTime())
            );
            statement.setLong(4, entity.getId());

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

    public void delete(final long id){
        var sql = "DELETE FROM employees WHERE id = ?";
        try(var connection = ConnectionUtil.getConnection();
            var statement = connection.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS)
        ){
            statement.setLong(1, id);
            statement.executeUpdate();

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
        var contact = new ContactEntity();
        var sql = "SELECT e.*, c.description, c.type " +
                "FROM employees e " +
                "INNER JOIN contacts c ON c.employee_id = e.id " +
                "WHERE e.id = ?";
        try(var connection = ConnectionUtil.getConnection();
            var statement = connection.prepareStatement(sql)
        ){
            statement.setLong(1, id);
            statement.executeQuery();

            var result = statement.getResultSet();
            if (result.next()){
                entity.setId(result.getLong("id"));
                entity.setName(result.getString("name"));
                entity.setSalary(result.getBigDecimal("salary"));
                var birthdayInstant = result.getTimestamp("birthday").toInstant();
                var birthday = OffsetDateTime.ofInstant(birthdayInstant, UTC);
                entity.setBirthday(birthday);

                contact.setDescription(result.getString("description"));
                contact.setType(result.getString("type"));
                entity.setContact(contact);
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
