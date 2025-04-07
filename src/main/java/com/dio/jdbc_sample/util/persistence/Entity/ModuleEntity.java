package com.dio.jdbc_sample.util.persistence.Entity;

import java.util.List;

public class ModuleEntity {
    private long id;
    private String name;
    private List<EmployeeEntity> employeeEntities;

    public List<EmployeeEntity> getEmployeeEntities() {
        return employeeEntities;
    }

    public void setEmployeeEntities(List<EmployeeEntity> employeeEntities) {
        this.employeeEntities = employeeEntities;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ModuleEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", employeeEntities=" + employeeEntities +
                '}';
    }
}
