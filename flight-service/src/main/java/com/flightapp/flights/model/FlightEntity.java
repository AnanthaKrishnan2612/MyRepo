package com.flightapp.flights.model;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FlightEntity {
	@Id
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

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date departureDate;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
	private Time departureTime;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date returnDate;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
	private Time returnTime;

}
