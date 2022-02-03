package com.flightapp.bookings.dto;

import java.sql.Date;
import java.sql.Time;

import com.flightapp.bookings.model.TypeOfMeal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FlightDTO {

	private String flightNumber;

	private String airline;

	private String origin;

	private String destination;

	private Date departureDateTime;

	private Date returnDateTime;

	private String scheduledDays;

	private Integer totalSeats;

	private String instrumentsUsed;

	private Integer totalNoOfRows;

	private Integer ticketCost;

	private TypeOfMeal meals;
	
	private Boolean isBlocked;
	
	private Boolean isOneWay;
	
	private Boolean isBooked;
	
	private Date departureDate;
	
	private Time departureTime;
	
	private Date returnDate;
	
	private Time returnTime;
	
	public FlightDTO(String flightNumber,Integer totalSeats, Boolean isBooked) {
		super();
		this.flightNumber = flightNumber;
		this.totalSeats = totalSeats;
		this.isBooked = isBooked;
	}
	
	
	

}
