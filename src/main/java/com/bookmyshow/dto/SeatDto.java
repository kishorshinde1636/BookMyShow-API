package com.bookmyshow.dto;

import com.bookmyshow.enums.SeatType;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SeatDto {
	
	private long seatId;

	// seatType
	private SeatType seatType;


}
