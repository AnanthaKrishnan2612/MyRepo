package com.flightapp.gatewayservice.DTO;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class JwtUserDetails  implements UserDetails {

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

//	public JwtUserDetails(final UserEntity userEntity) {
//		super(userEntity);
//	}
//	
	private Long id;

	private String username;

	private String email;

	@JsonIgnore
	private String password;
	
	private Collection<? extends GrantedAuthority> authorities;
	

	public JwtUserDetails( String username, String email, String password,
			Collection<? extends GrantedAuthority> authorities) {
		
		this.username = username;
		this.email = email;
		this.password = password;
		this.authorities = authorities;
	}
	
	public static JwtUserDetails build(String userName,String email,String password,Collection<? extends GrantedAuthority> authorities) {
		

		return new JwtUserDetails(
				userName,email,password,authorities);
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
