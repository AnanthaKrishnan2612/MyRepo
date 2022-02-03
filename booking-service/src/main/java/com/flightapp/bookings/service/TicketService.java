package com.flightapp.bookings.service;

import com.flightapp.bookings.dto.BookingDTO;

public interface TicketService {
	
	public BookingDTO getTicketInfoForPnr(Long pnrNumber);
	
	

}
