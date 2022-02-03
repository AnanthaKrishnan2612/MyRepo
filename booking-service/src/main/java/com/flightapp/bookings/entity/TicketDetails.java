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
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TicketDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long pnrNumber;

	private String userEmail;

	private String userName;

	private String flightNumber;

	private String startingLocation;

	private String destination;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date departureDate;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
	private Time departureTime;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date returnDate;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
	private Time returnTime;

	private Date departureDateTime;

	private Date returnDateTime;

	private Long contactNo;

	private Integer noOfSeats;

	private String bookingStatus;
	
	

}
