package com.dio.jdbc_sample.util.persistence.Entity;

public class ContactEntity {

    private long id;
    private String description;
    private String type;
    private EmployeeEntity employee;

    public EmployeeEntity getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeEntity employee) {
        this.employee = employee;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ContactEntity{" +
                "type='" + type + '\'' +
                ", description='" + description + '\''+
                '}';
    }
}
