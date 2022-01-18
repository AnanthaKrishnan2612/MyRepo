package com.flightapp.flights.exception;

public class FlightException extends Exception
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2574759984432634287L;

	public FlightException() {}

	public FlightException(String m) {
		super(m);
	}

	public FlightException(Exception e) {
		super(e);
	}

	public FlightException(String m, Exception e) {
		super(m, e);
	}
}
