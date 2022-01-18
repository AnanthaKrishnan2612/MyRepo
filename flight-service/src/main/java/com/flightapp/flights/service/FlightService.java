package com.flightapp.flights.service;

import java.sql.Date;
import java.util.List;

import com.flightapp.flights.dto.FlightDTO;
import com.flightapp.flights.exception.FlightException;

public interface FlightService {

	public FlightDTO addNewAirline(FlightDTO flight);

	public List<FlightDTO> blockFlights(String airline);

	public List<FlightDTO> unblockFlights(String airline);

	public List<FlightDTO> getAllUnblockedFlights();

	public FlightDTO getFlightByFlightNumber(String flightNumber);

	public List<FlightDTO> searchForFlights(Boolean isOneWay, String startingLocation, String destination,
			Date departureDate, Date returnDate) throws FlightException;

}
