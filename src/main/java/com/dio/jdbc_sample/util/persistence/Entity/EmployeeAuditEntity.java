package com.dio.jdbc_sample.util.persistence.Entity;

import java.math.BigDecimal;
import java.time.OffsetDateTime;


public record EmployeeAuditEntity(
        long employeeId,
        String name,
        String oldName,
        BigDecimal salary,
        BigDecimal old_salary,
        OffsetDateTime birthday,
        OffsetDateTime oldBirthday,
        OperationEnum operation) {


}
