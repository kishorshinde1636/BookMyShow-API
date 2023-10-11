package com.bookmyshow.exception;

import lombok.Getter;

@Getter
public class TicketCanNotBeCancelled extends RuntimeException {

	private String message;

	public TicketCanNotBeCancelled(String message) {
		super();
		this.message = message;
	}

}
