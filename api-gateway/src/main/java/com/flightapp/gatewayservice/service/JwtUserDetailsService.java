package com.flightapp.gatewayservice.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.flightapp.gatewayservice.DTO.JwtUserDetails;
import com.flightapp.gatewayservice.DTO.RegisterUserRequest;
import com.flightapp.gatewayservice.entity.UserEntity;
import com.flightapp.gatewayservice.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	private final PasswordEncoder passwordEncoder; 

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
        // find user from db where username = username

//        if ("admin".equals(username)) {
//        	Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
//			return new User("admin", "$2a$12$UncADuNPGujELKWupcDdsO7zBNAxOxJZwB..tE.dbA5SOwVrew1Oi", new ArrayList<>());
//		}else  {
//			Optional<UserEntity> optionalUserEntity = userRepository.findByUsername(username);
//			optionalUserEntity.orElseThrow(() -> new UsernameNotFoundException("Username not found"));
//			return optionalUserEntity.map(JwtUserDetails::new).get();
//		}
		if ("admin".equals(username)) {
        	Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        	authorities.add(new SimpleGrantedAuthority("ADMIN"));
			return new User("admin", "$2a$12$UncADuNPGujELKWupcDdsO7zBNAxOxJZwB..tE.dbA5SOwVrew1Oi", authorities);
		}else {
			Optional<UserEntity> optionalUserEntity = userRepository.findByUsername(username);
			if(optionalUserEntity.isPresent()) {
				UserEntity userEntity = optionalUserEntity.get();
				Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
	        	authorities.add(new SimpleGrantedAuthority(userEntity.getRole()));
				return new User (userEntity.getUsername(),userEntity.getPassword(),authorities);
			}else {
				throw new UsernameNotFoundException("User not found with username: " + username);
			}
		}
		
        
//			else {
//			throw new UsernameNotFoundException("User not found with username: " + username);
//		}
        
        
        
	}
	public void saveUser(RegisterUserRequest request) {
		UserEntity userEntity = new UserEntity();
		userEntity.setUsername(request.getUsername());
		userEntity.setEmail(request.getEmail());
		userEntity.setPassword(passwordEncoder.encode(request.getPassword()));
		userEntity.setRole("USER");
		userRepository.save(userEntity);
	}
}