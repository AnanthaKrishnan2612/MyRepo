package com.flightapp.bookings.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flightapp.bookings.entity.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, Integer> {

	public List<Passenger> findAllByPnrNumber(Long pnrNumber);
}
