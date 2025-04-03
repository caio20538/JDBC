DELIMITER $$
CREATE PROCEDURE prc_insert_employee(
    OUT p_id BIGINT,
    INT p_name VARCHAR(150),
    INT p_salary DECIMAL(10,2),
    INT p_birthday TIMESTAMP
)
BEGIN
    INSERT INTO employees (name, salary, birthday) VALUES (p_name, p_salary, p_birthday);
    SET p_id = LAST_INSERT_ID();
END $$