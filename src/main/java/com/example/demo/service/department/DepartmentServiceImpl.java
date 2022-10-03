package com.example.demo.service.department;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.department.Department;
import com.example.demo.model.department.DepartmentServiceResponse;
import com.example.demo.model.employee.EmployeeServiceResponse;
import com.example.demo.repository.department.DepartmentRepository;
import com.example.demo.service.employee.EmployeeService;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	EmployeeService employeeService;
	@Autowired
	DepartmentRepository departmentRepository;
	
	@Override
	public List<DepartmentServiceResponse> getAllDepartments() {
		List<DepartmentServiceResponse> response = new ArrayList<DepartmentServiceResponse>();
		List<Department> departmentList = departmentRepository.findAll();
		for (Department department : departmentList) {
			response.add(new DepartmentServiceResponse(
					department.getId(), 
					department.getName(),
					department.getCity()
			));
		}
		
		return response;
	}

	@Override
	public DepartmentServiceResponse getDepartmentById(long id) {
		Department department = departmentRepository.findById(id);
		if (department == null) {
			return null;
		}
		return new DepartmentServiceResponse(
				department.getId(), 
				department.getName(),
				department.getCity()
		);
	}

	@Override
	public int createDepartment(Department department) {
		return departmentRepository.create(department);
	}

	@Override
	public int updateDepartment(Department department) {
		return departmentRepository.update(department);
	}

	@Override
	public int deleteDepartmentById(long id) {
		return departmentRepository.deleteById(id);
	}

	@Override
	public List<EmployeeServiceResponse> getDepartmentEmployees(long departmentId) {
		return employeeService.getEmployeesByDepartmentId(departmentId);
	}

	@Override
	public DepartmentServiceResponse getDepartmentWithEmployees(long id) {
		DepartmentServiceResponse response = getDepartmentById(id);
		if (response == null) {
			return null;
		}
		List<EmployeeServiceResponse> employees = employeeService.getEmployeesByDepartmentId(id);
		response.setEmployeeList(employees);
		
		return response;
	}

}
