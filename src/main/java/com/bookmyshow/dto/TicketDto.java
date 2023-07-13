package com.bookmyshow.dto;

import com.bookmyshow.enums.TicketStatus;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TicketDto {

	private long ticketId;
	private long totalPrice;
	// ticketstatus
	private TicketStatus status;

}
