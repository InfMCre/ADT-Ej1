package com.example.demo.repository.employee;

import java.util.List;

import com.example.demo.exceptions.NotFoundConstraintException;
import com.example.demo.exceptions.EmployeeNotFoundException;
import com.example.demo.model.employee.Employee;

public interface EmployeeRepository {

	List<Employee> findAll();
	Employee findById(long id) throws EmployeeNotFoundException;
	int create(Employee employee) throws NotFoundConstraintException;
	int update(Employee employee);
	int deleteById(long id);
	List<Employee> findAllByDepartmentId(long departmentId);
	
}
