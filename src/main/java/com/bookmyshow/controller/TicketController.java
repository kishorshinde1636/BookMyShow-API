package com.bookmyshow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookmyshow.entity.Ticket;
import com.bookmyshow.service.TicketService;
import com.bookmyshow.util.ResponseStructure;

@RestController
@RequestMapping("/ticket")
public class TicketController {

	@Autowired
	private TicketService ticketService;

	@PostMapping
	public ResponseEntity<ResponseStructure<Ticket>> saveTicket(@RequestParam long showId,
			@RequestParam long customerId, @RequestParam long seatId) {
		return ticketService.saveTicket(showId, customerId, seatId);
	}

	@DeleteMapping
	public ResponseEntity<ResponseStructure<Ticket>> cancelTicket(@RequestParam long ticketId) {
		return ticketService.cancelTicket(ticketId);
	}
}
