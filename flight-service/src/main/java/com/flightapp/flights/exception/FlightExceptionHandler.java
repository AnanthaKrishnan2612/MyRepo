package com.flightapp.flights.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.flightapp.flights.model.ErrorResponseModel;



public class FlightExceptionHandler {
	
	@ExceptionHandler(FlightException.class)
	public ResponseEntity<ErrorResponseModel> handleFlightException(FlightException e){
		ErrorResponseModel errorResponseModel = new ErrorResponseModel();
		errorResponseModel.setErrorReportingTime(System.currentTimeMillis());
		errorResponseModel.setMessage(e.getMessage());
		errorResponseModel.setStatusCode(HttpStatus.NOT_FOUND.value());

		return new ResponseEntity<ErrorResponseModel>(errorResponseModel, HttpStatus.NOT_FOUND);
	}


}
