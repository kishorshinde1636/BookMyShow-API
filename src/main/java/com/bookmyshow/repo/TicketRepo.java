package com.bookmyshow.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookmyshow.entity.Ticket;

public interface TicketRepo extends JpaRepository<Ticket, Long> {

}
