package com.flightapp.bookings.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.flightapp.bookings.dto.BookingDTO;
import com.flightapp.bookings.service.TicketService;

@RestController
@RequestMapping("ticket")
public class TicketController {
	
	
	@Autowired
	private TicketService ticketService;
	
	@GetMapping("/{pnrNo}")
	public BookingDTO getTicketInfoForPnr(@PathVariable Long pnrNo,@RequestHeader HttpHeaders httpHeaders) {
		HttpServletRequest request = 
		        ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes())
		                .getRequest();
	
		return ticketService.getTicketInfoForPnr(pnrNo);
	}

}
