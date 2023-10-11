package com.bookmyshow.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bookmyshow.entity.Seat;
import com.bookmyshow.repo.SeatRepo;

@Repository
public class SeatDao {

	@Autowired
	private SeatRepo seatRepo;

	public Seat getSeatById(long seatId) {
		Optional<Seat> optional = seatRepo.findById(seatId);

		if (optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}

}
