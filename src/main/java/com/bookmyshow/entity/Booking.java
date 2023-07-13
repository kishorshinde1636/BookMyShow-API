package com.bookmyshow.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.bookmyshow.enums.BookingStatus;
import com.bookmyshow.enums.SeatType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Booking {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
     private long bookingId;
	
	private LocalDateTime bookingFromTime;
	private LocalDateTime bookingTillTime;
	
	private long seatId;
	private BookingStatus bookingStatus;
	
	private SeatType seatType;
	private double seatPrice;
}
