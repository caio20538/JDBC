CREATE TABLE contacts (
    id BIGINT NOT NULL AUTO_INCREMENT,
    description VARCHAR(50) NOT NULL,
    type VARCHAR(30),
    employee_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_employee_contact FOREIGN KEY (employee_id)
        REFERENCES employees(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

