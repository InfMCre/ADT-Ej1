package com.example.demo.model.employee;

import com.fasterxml.jackson.annotation.JsonInclude;

public class EmployeeServiceResponse {
	private Long id;
	private String name;
	private String position;
	private Long salary;
	private Long bossId;
	private Long departmentId;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private EmployeeServiceResponse boss;

	public EmployeeServiceResponse(
		Long id, 
		String name, 
		String position, 
		Long salary, 
		Long bossId, 
		Long departmentId,
		EmployeeServiceResponse boss
	) {
		super();
		this.id = id;
		this.name = name;
		this.position = position;
		this.salary = salary;
		this.bossId = bossId;
		this.departmentId = departmentId;
		this.boss = boss;
	}
	
	public EmployeeServiceResponse(
			Long id, 
			String name, 
			String position, 
			Long salary, 
			Long bossId, 
			Long departmentId
		) {
			super();
			this.id = id;
			this.name = name;
			this.position = position;
			this.salary = salary;
			this.bossId = bossId;
			this.departmentId = departmentId;
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

	public EmployeeServiceResponse getBoss() {
		return boss;
	}

	public void setBoss(EmployeeServiceResponse boss) {
		this.boss = boss;
	}

	@Override
	public String toString() {
		return "EmployeeServiceResponse [id=" + id + ", name=" + name + ", position=" + position + ", salary=" + salary
				+ ", bossId=" + bossId + ", departmentId=" + departmentId + ", boss=" + boss + "]";
	}
	
}
