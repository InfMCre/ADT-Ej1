package com.example.demo.service.employee;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exceptions.NotFoundConstraintException;
import com.example.demo.exceptions.EmployeeNotFoundException;
import com.example.demo.model.employee.Employee;
import com.example.demo.model.employee.EmployeeServiceResponse;
import com.example.demo.repository.employee.EmployeeRepository;


@Service
public class EmployeeServiceImpl implements EmployeeService {

	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Override
	public List<EmployeeServiceResponse> getAllEmployees() {
		List<EmployeeServiceResponse> response = new ArrayList<EmployeeServiceResponse>();
		List<Employee> employeeList = employeeRepository.findAll();
		for (Employee employee : employeeList) {
			response.add(new EmployeeServiceResponse(
					employee.getId(),
					employee.getName(),
					employee.getPosition(),
					employee.getSalary(),
					employee.getBossId(),
					employee.getDepartmentId()
			));
		}
		return response;
	}

	@Override
	public EmployeeServiceResponse getEmployeeById(long id) throws EmployeeNotFoundException {
		Employee employee = employeeRepository.findById(id);
		if (employee == null) {
			return null;
		}
		return new EmployeeServiceResponse(
				employee.getId(),
				employee.getName(),
				employee.getPosition(),
				employee.getSalary(),
				employee.getBossId(),
				employee.getDepartmentId()
		);
	}

	@Override
	public int createEmployee(Employee employee) throws NotFoundConstraintException {
		return employeeRepository.create(employee);
	}

	@Override
	public int updateEmployee(Employee employee) {
		return employeeRepository.update(employee);
	}

	@Override
	public int deleteEmployeeById(long id) {
		return employeeRepository.deleteById(id);
	}

	@Override
	public EmployeeServiceResponse getEmployeeWithBoss(long id) throws EmployeeNotFoundException {
		EmployeeServiceResponse employee = getEmployeeById(id);

		if (employee.getBossId() != null) {
			EmployeeServiceResponse boss = getEmployeeById(employee.getBossId());
			employee.setBoss(boss);
		}
		
		return employee;
	}

	@Override
	public List<EmployeeServiceResponse> getEmployeesByDepartmentId(long id) {
		List<EmployeeServiceResponse> response = new ArrayList<EmployeeServiceResponse>();
		List<Employee> employeeList = employeeRepository.findAllByDepartmentId(id);
		
		for (Employee employee : employeeList) {
			response.add(new EmployeeServiceResponse(
					employee.getId(),
					employee.getName(),
					employee.getPosition(),
					employee.getSalary(),
					employee.getBossId(),
					employee.getDepartmentId()
			));
		}
		return response;
	}

}
