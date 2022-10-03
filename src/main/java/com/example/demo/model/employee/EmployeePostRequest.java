package com.example.demo.model.employee;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class EmployeePostRequest {

	@NotNull(message="el nombre no puede ser nulo")
	@NotEmpty(message="el nombre no puede ser vacio")
	@NotBlank(message="el nombre no puede ser blanco")
	private String name;
	
	@NotNull(message="el nombre no puede ser nulo")
	@NotEmpty(message="el nombre no puede ser vacio")
	@NotBlank(message="el nombre no puede ser blanco")
	private String position;
	
	@Min(15000)
	@Max(30000)
	private Long salary;
	
	private Long bossId;
	private Long departmentId;
	
	public EmployeePostRequest() {}
	

	public EmployeePostRequest(
			@NotNull(message = "el nombre no puede ser nulo") @NotEmpty(message = "el nombre no puede ser vacio") @NotBlank(message = "el nombre no puede ser blanco") String name,
			@NotNull(message = "el nombre no puede ser nulo") @NotEmpty(message = "el nombre no puede ser vacio") @NotBlank(message = "el nombre no puede ser blanco") String position,
			@Min(15000) @Max(30000) Long salary, Long bossId, Long departmentId) {
		super();
		this.name = name;
		this.position = position;
		this.salary = salary;
		this.bossId = bossId;
		this.departmentId = departmentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Long getSalary() {
		return salary;
	}

	public void setSalary(Long salary) {
		this.salary = salary;
	}

	public Long getBossId() {
		return bossId;
	}

	public void setBossId(Long bossId) {
		this.bossId = bossId;
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	@Override
	public String toString() {
		return "EmployeePostRequest [name=" + name + ", position=" + position + ", salary=" + salary + ", bossId="
				+ bossId + ", departmentId=" + departmentId + "]";
	}
	
}
