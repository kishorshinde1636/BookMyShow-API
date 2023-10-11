package com.bookmyshow.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bookmyshow.entity.Ticket;
import com.bookmyshow.repo.TicketRepo;

@Repository
public class TicketDao {

	@Autowired
	private TicketRepo ticketRepo;

	public Ticket saveTicket(Ticket ticket) {

		return ticketRepo.save(ticket);

	}

	public Ticket getTicketById(long ticketId) {

		if (ticketRepo.findById(ticketId).isPresent()) {
			return ticketRepo.findById(ticketId).get();
		} else {
			return null;
		}

	}
}
