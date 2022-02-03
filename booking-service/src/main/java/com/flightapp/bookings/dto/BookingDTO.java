package com.flightapp.bookings.dto;

import java.util.List;

import com.flightapp.bookings.entity.Passenger;
import com.flightapp.bookings.entity.TicketDetails;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookingDTO {
	
	
	private FlightDTO flightDetails;
	
	private TicketDetails ticketDetails;
	
	private List<Passenger> passengerDetails;
	
	

}
