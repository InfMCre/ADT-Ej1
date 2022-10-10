package com.example.demo.repository.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.example.demo.model.user.User;

@Repository
public class JdbcUserRepository implements UserRepository {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public Optional<User> findByEmail(String email) {
		// veremos el optional mas adelante
		try {
			User user = jdbcTemplate.queryForObject("SELECT * from users where email = ?", BeanPropertyRowMapper.newInstance(User.class), email);
			return Optional.of(user);
		} catch (EmptyResultDataAccessException e){
			e.printStackTrace();
			return Optional.empty();
		}
	}

	@Override
	public int create(User user) {
		// IMPORTANTE: mantenemos aqui el encriptado de la password para asegurarnos que frente a cualquier creacion
		// la password va a ser encriptada. no es logica de negocio sino seguridad
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String password = passwordEncoder.encode(user.getPassword());
		
		// TODO podria darnos excepcion por que el email es unico		
		return jdbcTemplate.update("INSERT INTO users (email, password) VALUES (?, ?)",
			new Object[] { 
				user.getEmail(), 
				password
			}	
		);
	}

}
