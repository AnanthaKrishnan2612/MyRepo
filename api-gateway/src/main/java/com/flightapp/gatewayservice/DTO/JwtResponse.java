package com.flightapp.gatewayservice.DTO;

import java.io.Serializable;

public class JwtResponse implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
	private final String jwttoken;
	private final String  userNamwe;
	private final String  email;
	

	public JwtResponse(String jwttoken,String userName,String email) {
		this.jwttoken = jwttoken;
		this.userNamwe = userName;
		this.email = email;
	}

	public String getToken() {
		return this.jwttoken;
	}
	
	public String getUserName() {
		return this.userNamwe;
	}
	
	public String getEmail() {
		return this.email;
	}
}

