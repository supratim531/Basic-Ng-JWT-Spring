package com.company.auth.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.company.auth.app.jwt.JwtAuthenticationEntryPoint;
import com.company.auth.app.jwt.JwtAuthenticationFilter;
import com.company.auth.app.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
@SuppressWarnings("deprecation")
public class CustomWebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	private static final String H2 = "/h2db/**";
	private static final String LOGIN = "/api/auth/**";
	private static final String REGISTER = "/api/user/registerUser";
	private static final String EMPLOYEE_SERVICE = "/api/employee/**";
	private static final String GET_USER_SERVICE = "/api/user/getUserByUsername";

	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.cors();
		http
			.csrf().ignoringAntMatchers(H2)
			.and().headers().frameOptions().sameOrigin();
		http
			.csrf().disable()
			.authorizeRequests()
			.antMatchers(HttpHeaders.ALLOW).permitAll()
			.antMatchers(H2, LOGIN, REGISTER).permitAll()
			.antMatchers(EMPLOYEE_SERVICE, GET_USER_SERVICE).hasAnyRole("USER", "ADMIN")
			.antMatchers("**").hasRole("ADMIN")
			.anyRequest().authenticated();
		http.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint);
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailsService).passwordEncoder(this.passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder(10);
		return NoOpPasswordEncoder.getInstance();
	}

	@Bean
	public AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

}
