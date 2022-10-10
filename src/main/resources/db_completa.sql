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
)