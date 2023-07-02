package com.hospital.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hospital.entities.User;
import com.hospital.repositories.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findById(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with username" + username));
		return org.springframework.security.core.userdetails.User.withUsername(user.getUsername())
				.password(user.getPassword()).roles(user.getRole()).build();
	}
	
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		User user = userRepository.findById(username)
//				.orElseThrow(() -> new UsernameNotFoundException("User not found with username" + username));
//		return org.springframework.security.core.userdetails.User.builder()
//				.password(user.getPassword()).roles(user.getRole()).build();
//	}
	
	

}
