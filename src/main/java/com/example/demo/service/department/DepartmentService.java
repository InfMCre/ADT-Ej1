package com.example.demo.service.department;

import java.util.List;

import com.example.demo.model.department.Department;
import com.example.demo.model.department.DepartmentServiceResponse;
import com.example.demo.model.employee.EmployeeServiceResponse;

public interface DepartmentService {
	
	List<DepartmentServiceResponse> getAllDepartments();
	DepartmentServiceResponse getDepartmentById(long id);
	int createDepartment(Department department);
	int updateDepartment(Department department);
	int deleteDepartmentById(long id);
	List<EmployeeServiceResponse> getDepartmentEmployees(long departmentId);
	DepartmentServiceResponse getDepartmentWithEmployees(long id);
}
