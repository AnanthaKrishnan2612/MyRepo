package com.flightapp.flights.service;

import java.lang.reflect.Type;
import java.sql.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.flightapp.flights.dto.FlightDTO;
import com.flightapp.flights.exception.FlightException;
import com.flightapp.flights.model.FlightEntity;
import com.flightapp.flights.repository.FlightRepository;

@Service
public class FlightServiceImpl implements FlightService {
	
	
	private final FlightRepository flightRepository;
	private final ModelMapper modelMapper;
	
	

	public FlightServiceImpl(FlightRepository flightRepository,ModelMapper modelMapper) {
		this.flightRepository = flightRepository;
		this.modelMapper = modelMapper;
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
		Type dtoList = new TypeToken<List<FlightDTO>>(){}.getType();
		Type entityList = new TypeToken<List<FlightEntity>>(){}.getType();
		List<FlightDTO> flightDTOs = modelMapper.map(flightByAirline,dtoList);
		flightDTOs.forEach(eachFlight -> eachFlight.setIsBlocked(true));
		List<FlightEntity> flightEntities = modelMapper.map(flightDTOs,entityList);		
		return modelMapper.map(flightRepository.saveAll(flightEntities),dtoList);
	}

	@Override
	public List<FlightDTO> unblockFlights(String airline) {
		List<FlightEntity> flightByAirline = flightRepository.findAllByAirline(airline);
		Type dtoList = new TypeToken<List<FlightDTO>>(){}.getType();
		Type entityList = new TypeToken<List<FlightEntity>>(){}.getType();
		List<FlightDTO> flightDTOs = modelMapper.map(flightByAirline,dtoList);
		flightDTOs.forEach(eachFlight -> eachFlight.setIsBlocked(false));
		List<FlightEntity> flightEntities = modelMapper.map(flightDTOs,entityList);		
		return modelMapper.map(flightRepository.saveAll(flightEntities),dtoList);
	}

	@Cacheable(value="flights")
	@Override
	public List<FlightDTO> getAllUnblockedFlights() {
		Type dtoList = new TypeToken<List<FlightDTO>>(){}.getType();
		return modelMapper.map(flightRepository.findAllByisBlockedFalse(),dtoList);
	}

	@Override
	public FlightDTO getFlightByFlightNumber(String flightNumber) {
		
		return modelMapper.map(flightRepository.findById(flightNumber).get(),FlightDTO.class);
	}



	@Override
	public List<FlightDTO> searchForFlights(Boolean isOneWay, String startingLocation, String destination,
			Date departureDate, Date returnDate) throws FlightException {
		Type dtoList = new TypeToken<List<FlightDTO>>(){}.getType();
		if (isOneWay) {
			if (returnDate == null) {
				return modelMapper.map(flightRepository.findAllByisBlockedFalseAndOriginAndDestinationAndDepartureDate(
						startingLocation, destination, departureDate),dtoList);
			} else {
				throw new FlightException("Return Date is not Required");
			}
		} else {
			if (returnDate != null) {
				return modelMapper.map(flightRepository
						.findAllByisBlockedFalseAndOriginAndDestinationAndDepartureDateAndReturnDate(
								startingLocation, destination, departureDate, returnDate),dtoList);
			} else {
				throw new FlightException("Return Date is mandatory");
			}
		}
	}

}
