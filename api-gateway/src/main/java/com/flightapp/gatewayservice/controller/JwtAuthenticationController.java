package com.flightapp.gatewayservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flightapp.gatewayservice.DTO.JwtRequest;
import com.flightapp.gatewayservice.DTO.JwtResponse;
import com.flightapp.gatewayservice.DTO.JwtUserDetails;
import com.flightapp.gatewayservice.DTO.MessageResponse;
import com.flightapp.gatewayservice.DTO.RegisterUserRequest;
import com.flightapp.gatewayservice.service.JwtUserDetailsService;
import com.flightapp.gatewayservice.util.JwtUtils;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api")
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

//	@Autowired
//	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;
	
	@Autowired
	JwtUtils jwtUtils;

	
	@PostMapping("/admin/login")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

//		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
//		final UserDetails userDetails = userDetailsService
//				.loadUserByUsername(authenticationRequest.getUsername());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
//		final String token = jwtTokenUtil.generateToken(userDetails);
		JwtUserDetails userDetails = (JwtUserDetails) authentication.getPrincipal();	

		return ResponseEntity.ok(new JwtResponse(jwt,userDetails.getUsername(),userDetails.getEmail()));
	}


	@PostMapping("/user/register")
	public ResponseEntity<?> registerUser(@RequestBody RegisterUserRequest request){
		try {
		userDetailsService.saveUser(request);
		}
		catch(Exception E) {
			return ResponseEntity.badRequest().body("Error creating User"); 
		}
		return ResponseEntity.ok().body(new MessageResponse("User registered successfully!"));
		
		
	}
	
	@PostMapping("/user/login")
	public ResponseEntity<?> userLogin(@RequestBody  JwtRequest authenticationRequest) throws Exception{
		
//		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
//		final UserDetails userDetails = userDetailsService
//				.loadUserByUsername(authenticationRequest.getUsername());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
//		final String token = jwtTokenUtil.generateToken(userDetails);

		JwtUserDetails userDetails = (JwtUserDetails) authentication.getPrincipal();	

		return ResponseEntity.ok(new JwtResponse(jwt,userDetails.getUsername(),userDetails.getEmail()));
		
	}
	
	
//	private void authenticate(String username, String password) throws Exception {
//		try {
//			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
//		} catch (DisabledException e) {
//			throw new Exception("USER_DISABLED", e);
//		} catch (BadCredentialsException e) {
//			throw new Exception("INVALID_CREDENTIALS", e);
//		}
//	}
	
}