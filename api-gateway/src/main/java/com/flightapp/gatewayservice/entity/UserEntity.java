package com.flightapp.gatewayservice.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer Id;
	
	private String username;
	
	private String password;
	
	private String email;
	
	private String role;
	
	public UserEntity(UserEntity userEntity) {
		this.Id = userEntity.Id;
		this.email= userEntity.email;
		this.password = userEntity.password;
		this.username=userEntity.username;
				
	}

}
