package com.bookmyshow.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.bookmyshow.enums.SeatType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Seat {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long seatId;

	private SeatType seatType;

	@ManyToOne
	private Screen screen;
}
