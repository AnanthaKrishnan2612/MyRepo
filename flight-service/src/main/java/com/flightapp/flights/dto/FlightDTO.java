package com.flightapp.flights.dto;

import java.sql.Date;
import java.sql.Time;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
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

	private String equipmentUsed;

	private Integer totalNoOfRows;

	private Integer ticketCost;

	private String meals;

	private Boolean isOneWay;

	private Boolean isBlocked;

	private Boolean isBooked;

	private Date departureDate;

	private Time departureTime;

	private Date returnDate;

	private Time returnTime;

}
