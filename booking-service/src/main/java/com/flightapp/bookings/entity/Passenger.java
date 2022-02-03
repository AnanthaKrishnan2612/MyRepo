package com.flightapp.bookings.entity;

import java.sql.Time;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Passenger {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer passengerId;

	private String name;

	private String gender;

	private Integer age;

	private String seatNumber;

	private Boolean isMealReqd;

	private Long pnrNumber;



	
}
