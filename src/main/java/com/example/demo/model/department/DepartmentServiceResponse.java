package com.example.demo.model.department;

import java.util.List;

import com.example.demo.model.employee.EmployeeServiceResponse;
import com.fasterxml.jackson.annotation.JsonInclude;


public class DepartmentServiceResponse {
	
	private Long id;
	private String name;
	private String city;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	List<EmployeeServiceResponse> employeeList;

	public DepartmentServiceResponse(Long id, String name, String city, List<EmployeeServiceResponse> employeeList) {
		super();
		this.id = id;
		this.name = name;
		this.city = city;
		this.employeeList = employeeList;
	}
	
	public DepartmentServiceResponse(Long id, String name, String city) {
		super();
		this.id = id;
		this.name = name;
		this.city = city;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public List<EmployeeServiceResponse> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(List<EmployeeServiceResponse> employeeList) {
		this.employeeList = employeeList;
	}

	@Override
	public String toString() {
		return "DepartmentServiceResponse [id=" + id + ", name=" + name + ", city=" + city + ", employeeList="
				+ employeeList + "]";
	}
	
	
}
