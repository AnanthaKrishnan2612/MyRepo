package com.flightapp.flights.dto;

import java.sql.Date;
import java.sql.Time;

import com.flightapp.flights.model.TypeOfMeal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
//@Getter
//@Setter
public class FlightDTO {

	private String flightNumber;

	private String airline;

	private String origin;

	private String destination;

	private Date departureDateTime;

	private Date returnDateTime;

	private String scheduledDays;

	private Integer totalSeats;

	private String equipmentUsed;

	private Integer totalNoOfRows;

	private Integer ticketCost;

	private TypeOfMeal meals;

	private Boolean isOneWay;

	private Boolean isBlocked;

	private Boolean isBooked;

	private Date departureDate;

//	private Time departureTime;

	private Date returnDate;

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public String getAirline() {
		return airline;
	}

	public void setAirline(String airline) {
		this.airline = airline;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public Date getDepartureDateTime() {
		return departureDateTime;
	}

	public void setDepartureDateTime(Date departureDateTime) {
		this.departureDateTime = departureDateTime;
	}

	public Date getReturnDateTime() {
		return returnDateTime;
	}

	public void setReturnDateTime(Date returnDateTime) {
		this.returnDateTime = returnDateTime;
	}

	public String getScheduledDays() {
		return scheduledDays;
	}

	public void setScheduledDays(String scheduledDays) {
		this.scheduledDays = scheduledDays;
	}

	public Integer getTotalSeats() {
		return totalSeats;
	}

	public void setTotalSeats(Integer totalSeats) {
		this.totalSeats = totalSeats;
	}

	public String getEquipmentUsed() {
		return equipmentUsed;
	}

	public void setEquipmentUsed(String equipmentUsed) {
		this.equipmentUsed = equipmentUsed;
	}

	public Integer getTotalNoOfRows() {
		return totalNoOfRows;
	}

	public void setTotalNoOfRows(Integer totalNoOfRows) {
		this.totalNoOfRows = totalNoOfRows;
	}

	public Integer getTicketCost() {
		return ticketCost;
	}

	public void setTicketCost(Integer ticketCost) {
		this.ticketCost = ticketCost;
	}

	public TypeOfMeal getMeals() {
		return meals;
	}

	public void setMeals(TypeOfMeal meals) {
		this.meals = meals;
	}

	public Boolean getIsOneWay() {
		return isOneWay;
	}

	public void setIsOneWay(Boolean isOneWay) {
		this.isOneWay = isOneWay;
	}

	public Boolean getIsBlocked() {
		return isBlocked;
	}

	public void setIsBlocked(Boolean isBlocked) {
		this.isBlocked = isBlocked;
	}

	public Boolean getIsBooked() {
		return isBooked;
	}

	public void setIsBooked(Boolean isBooked) {
		this.isBooked = isBooked;
	}

	public Date getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

//	private Time returnTime;

}
