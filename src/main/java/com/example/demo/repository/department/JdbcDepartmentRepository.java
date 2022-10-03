package com.example.demo.repository.department;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.model.department.Department;


@Repository
public class JdbcDepartmentRepository implements DepartmentRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Department> findAll() {
		return jdbcTemplate.query("SELECT * FROM departments", BeanPropertyRowMapper.newInstance(Department.class));
	}

	@Override
	public Department findById(long id) {
		try {
			return jdbcTemplate.queryForObject("Select * from departments WHERE id = ?", BeanPropertyRowMapper.newInstance(Department.class), id);
		} catch (EmptyResultDataAccessException e){
			// no queremos que suelte una excepción si no hay elementos
			return null;
		}
	}

	@Override
	public int create(Department department) {
		return jdbcTemplate.update("INSERT INTO departments (name, city) VALUES (?, ?)",
			new Object[] { department.getName(), department.getCity() }	
		);
	}

	@Override
	public int update(Department department) {
		return jdbcTemplate.update("UPDATE departments SET name = ?, city = ? WHERE id = ?",
			new Object[] { department.getName(), department.getCity(), department.getId()}
		);
	}

	@Override
	public int deleteById(long id) {
		return jdbcTemplate.update("DELETE FROM departments WHERE id = ?", id);
	}

}
