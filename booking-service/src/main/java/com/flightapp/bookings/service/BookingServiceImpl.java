package com.flightapp.bookings.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.flightapp.bookings.controller.BookingController;
import com.flightapp.bookings.dto.BookingDTO;
import com.flightapp.bookings.dto.FlightDTO;
import com.flightapp.bookings.entity.Passenger;
import com.flightapp.bookings.entity.TicketDetails;
import com.flightapp.bookings.repository.BookingRepository;
import com.flightapp.bookings.repository.PassengerRepository;

@Service
public class BookingServiceImpl implements BookingService{

	@Autowired
	private KafkaTemplate<String, FlightDTO> kafkaTemplate;

	 private static final String TOPIC_CANCEL_FLIGHT = "cancelticket";
	 
	@Autowired
	private BookingRepository bookingrepository;

	@Autowired
	private TicketService ticketService;

	@Autowired
	private PassengerRepository passengerRepository;

	@Autowired
	private RestTemplate restTemplate;
	private static final Logger logger = LoggerFactory.getLogger(BookingServiceImpl.class);


	public List<TicketDetails> getTicketDetails(String emailId) {
		return bookingrepository.findAllByUserEmail(emailId);
	}

	public BookingDTO bookTicket(BookingDTO bookingDetails) {
		BookingDTO ticketBooked = new BookingDTO();
		TicketDetails ticketToBeCreated = bookingDetails.getTicketDetails();
		List<Passenger> passengerDetails = bookingDetails.getPassengerDetails();
		if (ticketToBeCreated.getNoOfSeats() == passengerDetails.size()) {
			TicketDetails ticketCreated = bookingrepository.save(ticketToBeCreated);
			passengerDetails.forEach(passenger -> passenger.setPnrNumber(ticketCreated.getPnrNumber()));
			List<Passenger> passengersAdded = passengerRepository.saveAll(passengerDetails);
			ticketBooked.setTicketDetails(ticketCreated);
			ticketBooked.setPassengerDetails(passengersAdded);
		}
		return ticketBooked;
	}

	public List<FlightDTO> searchFlights(FlightDTO flight) {
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<FlightDTO> request = new HttpEntity<>(flight, requestHeaders);

		ResponseEntity<FlightDTO[]> response = restTemplate.postForEntity("http://localhost:8088/airlines/airlines/search", request,
				FlightDTO[].class);
		return Arrays.asList(response.getBody());
	}

	public TicketDetails cancelTicket(Long pnrNo) throws Exception {
		BookingDTO bookingDetails = ticketService.getTicketInfoForPnr(pnrNo);
		
		Date date = new Date();
//		logger.info("depDate : {} and today date- :{} ", bookingDetails.getTicketDetails().getDepartureDate().getDate(),date.getDate());
		
		Long difference = bookingDetails.getTicketDetails().getDepartureDate().getTime()- date.getTime();
		
		logger.info("No of days to journey: {}" , TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS));
		if(difference >1) {
			FlightDTO flight = bookingDetails.getFlightDetails();
			flight.setIsBooked(false);
			flight.setTotalSeats(bookingDetails.getPassengerDetails().size());
//			kafkaTemplate.send(TOPIC_CANCEL_FLIGHT, flight);
			TicketDetails ticketDetails = bookingDetails.getTicketDetails();
			ticketDetails.setBookingStatus("CANCELLED");
			logger.info("Ticket Cancelled");
			return bookingrepository.save(ticketDetails);
		}else {
			throw new Exception("Ticket Cannot be cancelled");
		}
		
		
	}
}