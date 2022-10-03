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

import com.example.demo.model.department.Department;
import com.example.demo.model.department.DepartmentPostRequest;
import com.example.demo.model.department.DepartmentServiceResponse;
import com.example.demo.model.employee.EmployeeServiceResponse;
import com.example.demo.service.department.DepartmentService;

@RestController
@RequestMapping("api")
public class DepartmentController {

	@Autowired
	DepartmentService departmentService;
	
	@GetMapping("/departments")
	public ResponseEntity<List<DepartmentServiceResponse>> getAllDepartments() {
		return new ResponseEntity<List<DepartmentServiceResponse>>(departmentService.getAllDepartments(), HttpStatus.OK);
	}
	
	@GetMapping("/departments/{id}")
	public ResponseEntity<DepartmentServiceResponse> getDepartmentById(@PathVariable("id") long id) {
		DepartmentServiceResponse response = departmentService.getDepartmentById(id);
		if (response == null) {
			return new ResponseEntity<DepartmentServiceResponse>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<DepartmentServiceResponse>(response, HttpStatus.OK);
	}
	
	@PostMapping("/departments")
	public ResponseEntity<Integer> createDepartment(@Valid @RequestBody DepartmentPostRequest departmentPostRequest) {
		Department department = new Department(departmentPostRequest.getName(), departmentPostRequest.getCity());
		return new ResponseEntity<Integer>(departmentService.createDepartment(department), HttpStatus.CREATED);
	}
	
	@PutMapping("/departments/{id}")
	public ResponseEntity<Integer> updateDepartment(@PathVariable("id") long id, @Valid @RequestBody DepartmentPostRequest departmentPostRequest) {
		Department department = new Department(id, departmentPostRequest.getName(), departmentPostRequest.getCity());
		return new ResponseEntity<Integer>(departmentService.updateDepartment(department), HttpStatus.OK);
	}
	
	@DeleteMapping("/departments/{id}")
	public ResponseEntity<Integer> deleteDepartmentById(@PathVariable("id") long id) {
		return new ResponseEntity<Integer>(departmentService.deleteDepartmentById(id), HttpStatus.OK);
	}
	
	@GetMapping("/departments/{id}/employees")
	public ResponseEntity<List<EmployeeServiceResponse>> getDepartmentEmployeesByDepartmentId(@PathVariable("id") long id) {
		return new ResponseEntity<List<EmployeeServiceResponse>>(departmentService.getDepartmentEmployees(id), HttpStatus.OK);
	}
	
	@GetMapping("/departments/{id}/expandido")
	public ResponseEntity<DepartmentServiceResponse> getDepartmentEmployeesExpanded(@PathVariable("id") long id) {
		DepartmentServiceResponse response = departmentService.getDepartmentWithEmployees(id);
		if (response == null) {
			return new ResponseEntity<DepartmentServiceResponse>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<DepartmentServiceResponse>(response, HttpStatus.OK);
	}
	

}
