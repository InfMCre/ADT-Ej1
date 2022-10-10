package com.example.demo.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.user.AuthRequest;
import com.example.demo.model.user.AuthResponse;
import com.example.demo.model.user.User;
import com.example.demo.security.JwtTokenUtil;
import com.example.demo.service.user.UserService;




@RestController
public class AuthController {
	
	@Autowired 
	AuthenticationManager authManager;
	
	@Autowired 
	JwtTokenUtil jwtUtil;
	
	@Autowired
	UserService userService;
	
	@PostMapping("/auth/login")
	public ResponseEntity<?> login(@RequestBody @Valid AuthRequest request) {
		try {
			Authentication authentication = authManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
			);
			
			User user = (User) authentication.getPrincipal();
			String accessToken = jwtUtil.generateAccessToken(user);
			AuthResponse response = new AuthResponse(user.getEmail(), accessToken);
			
			return ResponseEntity.ok().body(response);
			
		} catch (BadCredentialsException ex) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}

	
	@PostMapping("/auth/signup")
	public ResponseEntity<?> signIn(@RequestBody @Valid AuthRequest request) {
		// TODO solo esta creado en el caso de que funcione. Si no es posible que de 500
		User user = new User(request.getEmail(), request.getPassword());
		return new ResponseEntity<Integer>(userService.create(user), HttpStatus.CREATED);
	}
	
	
	// utilizamos el /me por que vamos a coger el nuestro
	@GetMapping("/auth/me")
	public ResponseEntity<?> getUserInfo(Authentication authentication) {
		// aqui podemos castearlo a UserDetails o User. El UserDetails es una interfaz, 
		// si lo casteamos a la interfaz no podremos sacar campos como la ID
		User userDetails = (User) authentication.getPrincipal();
		
		// aqui podriamos devolver datos del usuario. quizá no sea lo que queremos devolver o no lo querramos devolver
		// es un ejemplo por que con userDetails.getId() tendríamos la ID del usuario sin que la pase por parametro
		// necesario en algunos servicios: si quiero devolver una lista o elemento privado del usuario, no voy a querer
		// que el usuario mande su ID por parametro. Ya que es trampeable.
		// de ahi que sea "/me" en el ejemplo
		
		return ResponseEntity.ok().body(userDetails);
	}
	
	
}
