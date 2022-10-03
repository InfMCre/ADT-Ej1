package com.example.demo.repository.employee;

import java.util.List;

import com.example.demo.model.employee.Employee;

public interface EmployeeRepository {

	List<Employee> findAll();
	Employee findById(long id);
	int create(Employee employee);
	int update(Employee employee);
	int deleteById(long id);
	List<Employee> findAllByDepartmentId(long departmentId);
	
}
