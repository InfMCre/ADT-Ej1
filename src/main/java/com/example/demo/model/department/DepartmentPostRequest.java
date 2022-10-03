package com.example.demo.model.department;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class DepartmentPostRequest {
	
	@NotNull(message="el nombre no puede ser nulo")
	@NotEmpty(message="el nombre no puede ser vacio")
	@NotBlank(message="el nombre no puede ser blanco")
	private String name;
	
	@NotNull(message="la ciudad no puede ser nula")
	@NotEmpty(message="la ciudad no puede ser vacia")
	@NotBlank(message="la ciudad no puede ser blanca")
	private String city;
	
	
	public DepartmentPostRequest(
			@NotNull(message = "el nombre no puede ser nulo") @NotEmpty(message = "el nombre no puede ser vacio") @NotBlank(message = "el nombre no puede ser blanco") String name,
			@NotNull(message = "la ciudad no puede ser nula") @NotEmpty(message = "la ciudad no puede ser vacia") @NotBlank(message = "la ciudad no puede ser blanca") String city) {
		super();
		this.name = name;
		this.city = city;
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
	
	@Override
	public String toString() {
		return "DepartmentPostRequest [name=" + name + ", city=" + city + "]";
	}
	
}
