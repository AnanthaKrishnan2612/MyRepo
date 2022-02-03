package com.flightapp.bookings.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flightapp.bookings.entity.TicketDetails;

public interface BookingRepository extends JpaRepository<TicketDetails, Long>{
	
	public List<TicketDetails>  findAllByUserEmail(String emailId);

	public Optional<TicketDetails> findByPnrNumber(Long pnrNumber);

}
