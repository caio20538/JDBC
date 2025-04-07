package com.dio.jdbc_sample.util.persistence.Entity;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

public class EmployeeEntity {
    private long id;
    private String name;
    private OffsetDateTime birthday;
    private BigDecimal salary;
    private List<ContactEntity> contact;
    private List<ModuleEntity> moduleEntities;

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

    public OffsetDateTime getBirthday() {
        return birthday;
    }

    public void setBirthday(OffsetDateTime birthday) {
        this.birthday = birthday;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public List<ContactEntity> getContact() {
        return contact;
    }

    public void setContact(List<ContactEntity> contact) {
        this.contact = contact;
    }

    public List<ModuleEntity> getModuleEntities() {
        return moduleEntities;
    }

    public void setModuleEntities(List<ModuleEntity> moduleEntities) {
        this.moduleEntities = moduleEntities;
    }

    @Override
    public String toString() {
        return "EmployeeEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                ", salary=" + salary +
                ", contact=" + contact +
                ", moduleEntities=" + moduleEntities +
                '}';
    }
}
