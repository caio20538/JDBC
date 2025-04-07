CREATE TABLE accesses(
    id_employee BIGINT NOT NULL,
    id_module BIGINT NOT NULL,
    PRIMARY KEY(id_employee, id_module),
    CONSTRAINT fk_accesses_employees FOREIGN KEY (id_employee) REFERENCES employees(id),
    CONSTRAINT fk_accesses_modules FOREIGN KEY (id_module) REFERENCES modules(id)
            ON DELETE CASCADE
            ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
