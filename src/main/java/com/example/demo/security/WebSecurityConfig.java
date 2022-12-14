package com.example.demo.security;


import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.repository.user.UserRepository;


@Configuration
@EnableGlobalMethodSecurity(
	prePostEnabled = true
)
public class WebSecurityConfig {
	
	@Autowired 
	private UserRepository userRepository;

	@Autowired 
	private JwtTokenFilter jwtTokenFilter;

	// carga los detalles de usuario.
	// la validez de la contraseña es automatica. Si es incorrecta no se loguea y devuelve 401
	@Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return userRepository.findByEmail(username)
                        .orElseThrow(
                                () -> new UsernameNotFoundException("User " + username + " not found"));
            }
        };
    }
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}
	
	// utilizado para encriptar las contraseñas en la DB
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	
	// aqui definimos principalmente cuales son las urls van a poder ser accesibles sin identificarse
	// y cuales seran obligatorias
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http.csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.authorizeRequests()
			.antMatchers("/api/auth/login").permitAll()
			.antMatchers("/api/auth/signup").permitAll()
			.anyRequest().authenticated(); // las demás requiere autenticacion
		
		// control de la excepcion : --> Devolver Unauthorized --> 401
		http.exceptionHandling()
			.authenticationEntryPoint(
				(request, response, ex) -> {
					response.sendError(
							HttpServletResponse.SC_UNAUTHORIZED,
							ex.getMessage()
					);
				}
			);

		http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
	}
}