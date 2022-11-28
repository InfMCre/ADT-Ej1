package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.exceptions.NotFoundConstraintException;
import com.example.demo.exceptions.EmployeeNotFoundException;
import com.example.demo.model.employee.Employee;
import com.example.demo.model.employee.EmployeePostRequest;
import com.example.demo.model.employee.EmployeeServiceResponse;
import com.example.demo.service.employee.EmployeeService;

@RestController
@RequestMapping("api")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;
	
	@GetMapping("/employees")
	public ResponseEntity<List<EmployeeServiceResponse>> getEmployees() {
		return new ResponseEntity<List<EmployeeServiceResponse>>(employeeService.getAllEmployees(), HttpStatus.OK);
	}
	
	@GetMapping("/employees/{id}")
	public ResponseEntity<EmployeeServiceResponse> getEmployeeById(@PathVariable("id") long id) {
		EmployeeServiceResponse response;
		try {
			response = employeeService.getEmployeeById(id);
			return new ResponseEntity<EmployeeServiceResponse>(response, HttpStatus.OK);
		} catch (EmployeeNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, e.getMessage(), e);
		}
	}
	
	@PostMapping("/employees")
	public ResponseEntity<Integer> createEmployee(@Valid @RequestBody EmployeePostRequest employeePostRequest) {
		
		
		try {
			Employee employee = new Employee(
					employeePostRequest.getName(), 
					employeePostRequest.getPosition(), 
					employeePostRequest.getSalary(),
					employeePostRequest.getBossId(), 
					employeePostRequest.getDepartmentId()
			);
			return new ResponseEntity<Integer>(employeeService.createEmployee(employee), HttpStatus.CREATED);
		} catch (NotFoundConstraintException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
		}

	}
	
	@PutMapping("/employees/{id}")
	public ResponseEntity<Integer> updateEmployee(@PathVariable("id") long id, @Valid @RequestBody EmployeePostRequest employeePostRequest) {
		Employee employee = new Employee(
				id,
				employeePostRequest.getName(), 
				employeePostRequest.getPosition(), 
				employeePostRequest.getSalary(),
				employeePostRequest.getBossId(), 
				employeePostRequest.getDepartmentId()
		);
		return new ResponseEntity<Integer>(employeeService.updateEmployee(employee), HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Integer> deleteEmployee(@PathVariable("id") long id) {
		return new ResponseEntity<Integer>(employeeService.deleteEmployeeById(id), HttpStatus.ACCEPTED);
	}
	
	
	@GetMapping("/employees/{id}/boss")
	public ResponseEntity<EmployeeServiceResponse> getEmployeeWithBoss(@PathVariable("id") long id) {
		EmployeeServiceResponse response;
		try {
			response = employeeService.getEmployeeWithBoss(id);
			return new ResponseEntity<EmployeeServiceResponse>(response, HttpStatus.OK);
		} catch (EmployeeNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, e.getMessage(), e);
		}
	}
	
}
