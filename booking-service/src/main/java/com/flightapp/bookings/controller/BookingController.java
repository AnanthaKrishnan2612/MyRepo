package com.flightapp.bookings.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flightapp.bookings.dto.BookingDTO;
import com.flightapp.bookings.dto.FlightDTO;
import com.flightapp.bookings.entity.TicketDetails;
import com.flightapp.bookings.service.BookingService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/bookings")
public class BookingController {
	
	@Autowired
	private BookingService bookingService;
	private static final Logger logger = LoggerFactory.getLogger(BookingController.class);
	@Autowired
	private KafkaTemplate<String, FlightDTO> kafkaTemplate;

	private static final String TOPIC_BOOK_FLIGHT = "bookticket";

	@PostMapping("/book")
	public BookingDTO bookTicket(@RequestBody BookingDTO ticketDetails) {
		FlightDTO flight = new FlightDTO(ticketDetails.getTicketDetails().getFlightNumber(),
				ticketDetails.getPassengerDetails().size(), true);
//		kafkaTemplate.send(TOPIC_BOOK_FLIGHT, flight);
		return bookingService.bookTicket(ticketDetails);
	}

	@PostMapping("/cancel/{pnrNo}")
	public TicketDetails cancelTicket(@PathVariable Long pnrNo) throws Exception {
		logger.info("Cancel ticket reequested for PNR : {}" , pnrNo);
		return bookingService.cancelTicket(pnrNo);
	}

	/**
	 * Get Booked tickets history based on Email ID
	 * 
	 * @param emailId
	 * @return
	 */
	@GetMapping("/history/{emailId}")
	public List<TicketDetails> getTicketDetailsByUser(@PathVariable(value = "emailId") String emailId) {
		logger.info("email :{}" , emailId);
		return bookingService.getTicketDetails(emailId);

	}

	@PostMapping("/flight/search")
	public List<FlightDTO> searchFlights(@RequestBody FlightDTO flight) {

		return bookingService.searchFlights(flight);
	}

}
