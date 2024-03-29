package com.bookmyshow.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.bookmyshow.enums.TicketStatus;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long ticketId;
	private double totalPrice;
	// ticketstatus
	private TicketStatus status;

	@ManyToOne
	private MovieShow movieShow;

	@OneToMany
	private List<Booking> bookings;

	@ManyToOne
	@JoinColumn
	private Customer customer;

}
