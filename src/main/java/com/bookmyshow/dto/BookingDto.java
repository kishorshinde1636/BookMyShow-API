package com.bookmyshow.dto;

import java.time.LocalDateTime;

import com.bookmyshow.enums.BookingStatus;
import com.bookmyshow.enums.SeatType;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BookingDto {

	private long bookingId;

	private LocalDateTime bookingFromTime;
	private LocalDateTime bookingTillTime;

	private long seatId;
	// bookingstatus
	private BookingStatus bookingStatus;

    //seatType
	private SeatType seatType;
	private double seatPrice;

}
