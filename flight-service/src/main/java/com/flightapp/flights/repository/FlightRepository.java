package com.flightapp.flights.repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.flightapp.flights.entity.FlightEntity;



@Repository
public interface FlightRepository extends JpaRepository<FlightEntity, String> {

	@Query
	List<FlightEntity> findAllByAirline(String airline);

	@Query
	List<FlightEntity> findAllByisBlockedFalse();

	@Query
	List<FlightEntity> findAllByisBlockedNull();

	@Query
	List<FlightEntity> findAllByisBlockedFalseAndOriginAndDestinationAndDepartureDateTime(String origin,
			String destination, Date departureDateTime);

	@Query
	List<FlightEntity> findAllByisBlockedFalseAndOriginAndDestinationAndDepartureDateTimeAndReturnDateTime(
			String origin, String destination, Date departureDateTime, Date returnDateTime);

	@Query
	Optional<FlightEntity> findByFlightNumber(String flightNumber);

	@Query
	List<FlightEntity> findAllByisBlockedFalseAndOriginAndDestinationAndDepartureDate(String origin,
			String destination, Date departureDate);

	@Query
	List<FlightEntity> findAllByisBlockedFalseAndOriginAndDestinationAndDepartureDateAndReturnDate(
			String origin, String destination, Date departureDate, Date returnDate);

}
