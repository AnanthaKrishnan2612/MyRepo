package com.flightapp.flights.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flightapp.flights.dto.FlightDTO;
import com.flightapp.flights.service.FlightService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/airlines")
public class FlightController {

	private final FlightService flightService;
	private static final Logger logger = LoggerFactory.getLogger(FlightController.class);
	
	
	public FlightController(FlightService flightService) {
		this.flightService = flightService;

	}

	@PostMapping("/register")
	public ResponseEntity<FlightDTO> addNewAirline(@RequestBody FlightDTO flightDto) {

		return ResponseEntity.ok(flightService.addNewAirline(flightDto));
	}
	@PutMapping("/inventory/add")
	public ResponseEntity<FlightDTO> updateAirlineWithInventoryOrSchedule( @RequestBody FlightDTO flight)
			throws Exception {
		return ResponseEntity.ok(flightService.updateAirlineWithInventoryOrSchedule(flight));
	}

	@PutMapping("/block/{airline}")
	public ResponseEntity<List<FlightDTO>> blockFlights(@PathVariable String airline) {
		return ResponseEntity.ok(flightService.blockFlights(airline));
	}

	@PutMapping("/unblock/{airline}")
	public ResponseEntity<List<FlightDTO>> unblockFlights(@PathVariable String airline) {
		return ResponseEntity.ok(flightService.unblockFlights(airline));
	}

	@GetMapping("/allflights")
	public ResponseEntity<List<FlightDTO>> getAllUnblockedFlights() {
		return ResponseEntity.ok(flightService.getAllUnblockedFlights());
	}

	@GetMapping("/flight/{flightNumber}")
	public ResponseEntity<FlightDTO> getFlightByFlightNumber(@PathVariable String flightNumber) {
		return ResponseEntity.ok(flightService.getFlightByFlightNumber(flightNumber));
	}

	@PostMapping("/search")
	public ResponseEntity<List<FlightDTO>> getFlightsBySearchParam(@RequestBody FlightDTO flights) throws Exception {
		logger.info("SearchFlight request :  " ,flights.getOrigin(),flights.getDestination(),flights.getIsOneWay(),flights.getDepartureDate(),flights.getReturnDate());
		ResponseEntity<List<FlightDTO>> response = ResponseEntity.ok(flightService.searchForFlights(flights.getIsOneWay(), flights.getOrigin(),
				flights.getDestination(), flights.getDepartureDate(), flights.getReturnDate()));
		logger.info("Flights retrieved by getFlightsBySearchParam : {} ", response.getBody());
		
		return response;
	}

}
