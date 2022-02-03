package com.flightapp.bookings.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.flightapp.bookings.dto.BookingDTO;
import com.flightapp.bookings.dto.FlightDTO;
import com.flightapp.bookings.entity.Passenger;
import com.flightapp.bookings.entity.TicketDetails;
import com.flightapp.bookings.repository.BookingRepository;
import com.flightapp.bookings.repository.PassengerRepository;

public class TicketServiceImpl implements TicketService {
	
	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private PassengerRepository passengerRepository;

	@Autowired
	private RestTemplate restTemplate;
	
	public BookingDTO getTicketInfoForPnr(Long pnrNumber) {
		BookingDTO ticketBooked = new BookingDTO();
		Optional<TicketDetails> bookedTicket = bookingRepository.findByPnrNumber(pnrNumber);
		if(bookedTicket.isPresent()) {
			ticketBooked.setTicketDetails(bookedTicket.get());
		}

		List<Passenger> passengersByPnr = passengerRepository.findAllByPnrNumber(pnrNumber);
		ticketBooked.setPassengerDetails(passengersByPnr);
		
		HttpServletRequest request = 
		        ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes())
		                .getRequest();
		
	
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		requestHeaders.add("Authorization", request.getHeader("Authorization"));
		HttpEntity<FlightDTO> requestEntity = new HttpEntity<FlightDTO>(null, requestHeaders);
		ResponseEntity<FlightDTO> response = restTemplate
				 .exchange("http://FLIGHT-SERVICE//flights/flight/" + bookedTicket.get().getFlightNumber(), HttpMethod.GET, requestEntity, FlightDTO.class);
		
		ticketBooked.setFlightDetails(response.getBody());

		return ticketBooked;
	}


}
