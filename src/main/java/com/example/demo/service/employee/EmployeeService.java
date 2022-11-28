package com.example.demo.service.employee;

import java.util.List;

import com.example.demo.exceptions.NotFoundConstraintException;
import com.example.demo.exceptions.EmployeeNotFoundException;
import com.example.demo.model.employee.Employee;
import com.example.demo.model.employee.EmployeeServiceResponse;

public interface EmployeeService {
	
	List<EmployeeServiceResponse> getAllEmployees();
	EmployeeServiceResponse getEmployeeById(long id) throws EmployeeNotFoundException;
	int createEmployee(Employee employee) throws NotFoundConstraintException;
	int updateEmployee(Employee employee);
	int deleteEmployeeById(long id);
	EmployeeServiceResponse getEmployeeWithBoss(long id) throws EmployeeNotFoundException;
	List<EmployeeServiceResponse> getEmployeesByDepartmentId(long id);

}
