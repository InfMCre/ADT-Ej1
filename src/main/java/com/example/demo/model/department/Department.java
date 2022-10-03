package com.example.demo.model.department;

public class Department {
	private Long id;
	private String name;
	private String city;
	
	public Department() {}
	
	public Department(Long id, String name, String city) {
		super();
		this.id = id;
		this.name = name;
		this.city = city;
	}
	
	public Department(String name, String city) {
		super();
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

	@Override
	public String toString() {
		return "Department [id=" + id + ", name=" + name + ", city=" + city + "]";
	}
	
}
