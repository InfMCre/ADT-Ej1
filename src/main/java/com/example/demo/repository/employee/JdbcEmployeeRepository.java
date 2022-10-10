package com.example.demo.repository.employee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.model.employee.Employee;


@Repository
public class JdbcEmployeeRepository implements EmployeeRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Employee> findAll() {
		return jdbcTemplate.query("SELECT * FROM employees", BeanPropertyRowMapper.newInstance(Employee.class));
	}

	@Override
	public Employee findById(long id) {
		try {
			return jdbcTemplate.queryForObject("Select * from employees WHERE id = ?", BeanPropertyRowMapper.newInstance(Employee.class), id);
		} catch (EmptyResultDataAccessException e){
			// no queremos que suelte una excepción si no hay elementos
			return null;
		}
	}

	@Override
	public int create(Employee employee) {
		return jdbcTemplate.update("INSERT INTO employees (name, position, salary, bossId, departmentId) VALUES (?, ?, ?, ?, ?)",
			new Object[] { 
				employee.getName(), 
				employee.getPosition(), 
				employee.getSalary(), 
				employee.getBossId(), 
				employee.getDepartmentId() 
			}	
		);
	}

	@Override
	public int update(Employee employee) {
		return jdbcTemplate.update("UPDATE employees SET name = ?, position = ?, salary = ?, bossId = ?, departmentId = ? WHERE id = ?",
			new Object[] { 
					employee.getName(), 
					employee.getPosition(), 
					employee.getSalary(), 
					employee.getBossId(), 
					employee.getDepartmentId(), 
				employee.getId()
			}
		);
	}

	@Override
	public int deleteById(long id) {
		return jdbcTemplate.update("DELETE FROM employees WHERE id = ?", id);
	}

	@Override
	public List<Employee> findAllByDepartmentId(long departmentId) {
		return jdbcTemplate.query("SELECT * FROM employees WHERE departmentId = ?", BeanPropertyRowMapper.newInstance(Employee.class), departmentId);
	}

}
