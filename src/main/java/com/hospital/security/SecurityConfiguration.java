package com.hospital.security;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRepository;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	@Autowired private JwtRequestFilter jwtRequestFilter;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
				.antMatchers(HttpMethod.POST, "/admin/authenticate").permitAll()
				//Admin
				.antMatchers(HttpMethod.POST, "/admin/doctor/add").hasRole("admin")
				.antMatchers(HttpMethod.GET, "/admin/doctor/list").hasRole("admin")
				.antMatchers(HttpMethod.DELETE, "/admin/doctor/delete").hasRole("admin")
				.antMatchers(HttpMethod.PATCH, "/admin/doctor/update").hasRole("admin")
				.antMatchers(HttpMethod.POST, "/admin/appointment/add").hasRole("admin")
				.antMatchers(HttpMethod.DELETE, "/admin/appointment/delete").hasRole("admin")
				.antMatchers(HttpMethod.PATCH, "/admin/appointment/update").hasRole("admin")
				.antMatchers(HttpMethod.POST, "/admin/patient/add").hasRole("admin")
				.antMatchers(HttpMethod.DELETE, "/admin/patient/delete").hasRole("admin")
				.antMatchers(HttpMethod.PATCH, "/admin/patient/update").hasRole("admin")
				.antMatchers(HttpMethod.POST, "/admin/receptionist/add").hasRole("admin")
				.antMatchers(HttpMethod.GET, "/admin/receptionist/list").hasRole("admin")
				.antMatchers(HttpMethod.DELETE, "/admin/receptionist/delete").hasRole("admin")
				//Doctor
				.antMatchers(HttpMethod.GET, "/doctor/appointment/list").hasAnyRole("doctor","admin")
                .antMatchers(HttpMethod.GET, "/doctor/patient/list").hasAnyRole("doctor","admin")
                .antMatchers(HttpMethod.PATCH, "/doctor/update/appointmentStatus").hasAnyRole("doctor","admin")
                //Receptionist
                .antMatchers(HttpMethod.GET, "/receptionist/appointment/list").hasAnyRole("receptionist","admin")
                .antMatchers(HttpMethod.POST, "/receptionist/appointment/add").hasAnyRole("receptionist","admin")
                .antMatchers(HttpMethod.PATCH, "/receptionist/appointment/update").hasAnyRole("receptionist","admin")
				.antMatchers("/receptionist/**").hasRole("receptionist")
				.anyRequest().authenticated()
				.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
		
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
//	@Bean
//    public CsrfTokenRepository csrfTokenRepository() {
//        return new CookieCsrfTokenRepository();
//    }
}
