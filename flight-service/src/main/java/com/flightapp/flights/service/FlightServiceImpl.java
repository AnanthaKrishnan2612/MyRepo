package com.flightapp.flights.service;

import java.lang.reflect.Type;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.flightapp.flights.dto.FlightDTO;
import com.flightapp.flights.entity.FlightEntity;
import com.flightapp.flights.exception.FlightException;
import com.flightapp.flights.repository.FlightRepository;

@Service
public class FlightServiceImpl implements FlightService {

	private final FlightRepository flightRepository;
	private final ModelMapper modelMapper;

	public FlightServiceImpl(FlightRepository flightRepository, ModelMapper modelMapper) {
		this.flightRepository = flightRepository;
		this.modelMapper = modelMapper;

	}

	@Override
//	@KafkaListener(topics = "bookticket", groupId = "group_id", containerFactory = "userKafkaListenerFactory")
//	@KafkaListener(topics = "cancelticket", groupId = "group_id", containerFactory = "userKafkaListenerFactory")
	public FlightDTO updateAirlineWithInventoryOrSchedule(FlightDTO flightToBeUpdated) throws Exception {
		Optional<FlightEntity> optionalFlight = flightRepository
				.findByFlightNumber(flightToBeUpdated.getFlightNumber());
		if (optionalFlight.isPresent()) {
			System.out.println("no of seats - " + flightToBeUpdated.getTotalSeats());
			System.out.println("Booking status - " + flightToBeUpdated.getIsBooked());
			FlightDTO flightDto = modelMapper.map(optionalFlight.get(), FlightDTO.class);
			flightDto.setReturnDate(flightToBeUpdated.getReturnDate() == null ? flightDto.getReturnDate()
					: flightToBeUpdated.getReturnDate());
//			flightDto.setDepartureTime(flightToBeUpdated.getDepartureTime() == null ? flightDto.getDepartureTime()
//					: flightToBeUpdated.getDepartureTime());
//			flightDto.setReturnTime(flightToBeUpdated.getReturnTime() == null ? flightDto.getReturnTime()
//					: flightToBeUpdated.getReturnTime());
			flightDto.setEquipmentUsed(
					flightToBeUpdated.getEquipmentUsed() == null || flightToBeUpdated.getEquipmentUsed().isEmpty()
							? flightDto.getEquipmentUsed()
							: flightToBeUpdated.getEquipmentUsed());
			flightDto.setDestination(flightToBeUpdated.getDestination() == null ? flightDto.getDestination()
					: flightToBeUpdated.getDestination());
			flightDto.setOrigin(
					flightToBeUpdated.getOrigin() == null ? flightDto.getOrigin() : flightToBeUpdated.getOrigin());
			flightDto.setTicketCost(flightToBeUpdated.getTicketCost() == null ? flightDto.getTicketCost()
					: flightToBeUpdated.getTicketCost());
			flightDto.setTotalNoOfRows(flightToBeUpdated.getTotalNoOfRows() == null ? flightDto.getTotalNoOfRows()
					: flightToBeUpdated.getTotalNoOfRows());
			flightDto.setIsBooked(flightToBeUpdated.getIsBooked());
			flightDto.setIsBlocked(flightToBeUpdated.getIsBlocked());
			if (flightToBeUpdated.getIsBooked()) {
				flightDto.setTotalSeats(flightToBeUpdated.getTotalSeats() == null ? flightDto.getTotalSeats()
						: flightDto.getTotalSeats() - flightToBeUpdated.getTotalSeats());
			} else {
				flightDto.setTotalSeats(flightToBeUpdated.getTotalSeats() == null ? flightDto.getTotalSeats()
						: flightDto.getTotalSeats() + flightToBeUpdated.getTotalSeats());
			}
			flightDto.setMeals(
					flightToBeUpdated.getMeals() == null ? flightDto.getMeals() : flightToBeUpdated.getMeals());

			return modelMapper.map(flightRepository.save(modelMapper.map(flightDto, FlightEntity.class)),
					FlightDTO.class);
		} else {
			throw new Exception("Flight not found for FlightNumber :: " + flightToBeUpdated.getFlightNumber());
		}
	}

	@Override
	public FlightDTO addNewAirline(FlightDTO flightDto) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		FlightEntity flightEntity = flightRepository.save(modelMapper.map(flightDto, FlightEntity.class));
		return modelMapper.map(flightEntity, FlightDTO.class);
	}

	@Override
	public List<FlightDTO> blockFlights(String airline) {

		List<FlightEntity> flightByAirline = flightRepository.findAllByAirline(airline);
		Type dtoList = new TypeToken<List<FlightDTO>>() {
		}.getType();
		Type entityList = new TypeToken<List<FlightEntity>>() {
		}.getType();
		List<FlightDTO> flightDTOs = modelMapper.map(flightByAirline, dtoList);
		flightDTOs.forEach(eachFlight -> eachFlight.setIsBlocked(true));
		List<FlightEntity> flightEntities = modelMapper.map(flightDTOs, entityList);
		return modelMapper.map(flightRepository.saveAll(flightEntities), dtoList);
	}

	@Override
	public List<FlightDTO> unblockFlights(String airline) {
		List<FlightEntity> flightByAirline = flightRepository.findAllByAirline(airline);
		Type dtoList = new TypeToken<List<FlightDTO>>() {
		}.getType();
		Type entityList = new TypeToken<List<FlightEntity>>() {
		}.getType();
		List<FlightDTO> flightDTOs = modelMapper.map(flightByAirline, dtoList);
		flightDTOs.forEach(eachFlight -> eachFlight.setIsBlocked(false));
		List<FlightEntity> flightEntities = modelMapper.map(flightDTOs, entityList);
		return modelMapper.map(flightRepository.saveAll(flightEntities), dtoList);
	}

//	@Cacheable(value = "flights")
	@Override
	public List<FlightDTO> getAllUnblockedFlights() {
		Type dtoList = new TypeToken<List<FlightDTO>>() {
		}.getType();
//		List<FlightDTO> flightDTOs = modelMapper.map(flightRepository.findAllByisBlockedFalse(), dtoList);
//		System.out.println(flightDTOs);
		return modelMapper.map(flightRepository.findAllByisBlockedFalse(), dtoList);
	}

	@Override
	public FlightDTO getFlightByFlightNumber(String flightNumber) {

		return modelMapper.map(flightRepository.findById(flightNumber).get(), FlightDTO.class);
	}

	@Override
	public List<FlightDTO> searchForFlights(Boolean isOneWay, String startingLocation, String destination,
			Date departureDate, Date returnDate) throws FlightException {
		Type dtoList = new TypeToken<List<FlightDTO>>() {
		}.getType();
		if (isOneWay) {
			if (returnDate == null) {
				return modelMapper.map(flightRepository.findAllByisBlockedFalseAndOriginAndDestinationAndDepartureDate(
						startingLocation, destination, departureDate), dtoList);
			} else {
				throw new FlightException("Return Date is not Required");
			}
		} else {
			if (returnDate != null) {
				return modelMapper.map(
						flightRepository.findAllByisBlockedFalseAndOriginAndDestinationAndDepartureDateAndReturnDate(
								startingLocation, destination, departureDate, returnDate),
						dtoList);
			} else {
				throw new FlightException("Return Date is mandatory");
			}
		}
	}

}
