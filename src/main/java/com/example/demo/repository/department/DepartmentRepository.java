package com.example.demo.repository.department;

import java.util.List;

import com.example.demo.model.department.Department;

public interface DepartmentRepository {
	List<Department> findAll();
	Department findById(long id);
	int create(Department department);
	int update(Department department);
	int deleteById(long id);
}
