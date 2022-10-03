package com.example.demo.service.employee;

import java.util.List;

import com.example.demo.model.employee.Employee;
import com.example.demo.model.employee.EmployeeServiceResponse;

public interface EmployeeService {
	
	List<EmployeeServiceResponse> getAllEmployees();
	EmployeeServiceResponse getEmployeeById(long id);
	int createEmployee(Employee employee);
	int updateEmployee(Employee employee);
	int deleteEmployeeById(long id);
	EmployeeServiceResponse getEmployeeWithBoss(long id);
	List<EmployeeServiceResponse> getEmployeesByDepartmentId(long id);

}
