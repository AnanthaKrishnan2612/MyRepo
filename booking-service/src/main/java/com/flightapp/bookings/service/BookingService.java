package com.flightapp.bookings.service;

import java.util.List;

import com.flightapp.bookings.dto.BookingDTO;
import com.flightapp.bookings.dto.FlightDTO;
import com.flightapp.bookings.entity.TicketDetails;

public interface BookingService {
	
	public List<TicketDetails> getTicketDetails(String emailId);
	
	public BookingDTO bookTicket(BookingDTO bookingDetails);
	
	public List<FlightDTO> searchFlights(FlightDTO flight);
	
	public TicketDetails cancelTicket(Long pnrNo) throws Exception;

}
