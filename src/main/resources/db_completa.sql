DROP DATABASE IF EXISTS ejercicio1;
CREATE DATABASE ejercicio1;
USE ejercicio1;

# DROP TABLE IF EXISTS EMPLOYEES;
CREATE TABLE DEPARTMENTS (
	id INT PRIMARY KEY AUTO_INCREMENT, 
    name VARCHAR(255), 
    city VARCHAR(255)
);

# DROP TABLE IF EXISTS EMPLOYEES;
CREATE TABLE EMPLOYEES (
	id INT PRIMARY KEY AUTO_INCREMENT, 
    name VARCHAR(255), 
    position VARCHAR(255), 
    salary INT,
    bossId INT,
    departmentId INT,
    CONSTRAINT fk_bossId_employee_id FOREIGN KEY (bossId) REFERENCES employees(id),
    CONSTRAINT fk_departmentId_deparment_id FOREIGN KEY (departmentId) REFERENCES departments(id)
);


INSERT INTO departments 
	(name, city) 
VALUES 
	('Financiero', 'Bilbao'),
	('Recursos Humanos', 'Bilbao'),
	('Marketing', 'Vitoria'),
	('Comercial', 'Vitoria'),
	('Compras', 'Vitoria'),
	('Logística y Operaciones', 'Bilbao'),
	('Control de Gestión', 'San Sebastián'),
	('Dirección General', 'San Sebastián');

INSERT INTO employees 
	(name, position, salary, bossId, departmentId) 
VALUES 
	('IRULEGI','DIRECTOR',30000, null, 1),
	('ALONSO','PRESIDENTE',45000, 1, 2),
	('MARTINEZ','TRABAJADOR',19800, 1, 3),
	('LOPEZ','TRABAJADOR',17500, 2, 1),
	('ORONOZ','COMERCIAL',22000, 2, 2),
	('LERTXUNDI','COMERCIAL',21900, 3, 1);


CREATE TABLE users (
  id int NOT NULL AUTO_INCREMENT,
  email varchar(50) NOT NULL,
  password varchar(64) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY (email)
);

# DROP TABLE IF EXISTS SALES;
CREATE TABLE SALES (
	id INT PRIMARY KEY AUTO_INCREMENT, 
    saleDate DATE, 
    amount INT,
    employeeId INT,
    CONSTRAINT fk_employeeId_employee_id FOREIGN KEY (employeeId) REFERENCES employees(id)
);

INSERT INTO sales 
	(saleDate, amount, employeeId) 
VALUES 
	('2022-10-01', 20000, 1),
    ('2022-10-01', 25000, 2),
    ('2022-10-01', 17000, 3),
    ('2022-10-02', 18000, 3),
    ('2022-10-02', 30000, 5),
    ('2022-10-03', 15000, 6),
    ('2022-10-03', 21000, 6),
    ('2022-10-04', 25000, 1),
    ('2022-10-04', 17000, 1),
    ('2022-10-04', 22000, 5),
    ('2022-10-10', 25000, 5),
    ('2022-10-10', 14000, 6),
    ('2022-10-10', 23500, 6),
    ('2022-10-11', 21000, 5),
    ('2022-10-11', 19500, 5),
    ('2022-10-12', 20000, 6),
    ('2022-10-13', 25000, 6),
    ('2022-10-14', 17000, 1);
    