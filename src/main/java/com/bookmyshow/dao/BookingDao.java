package com.bookmyshow.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bookmyshow.entity.Booking;
import com.bookmyshow.repo.BookingRepo;

@Repository
public class BookingDao {

	@Autowired
	private BookingRepo bookingRepo;

	public Booking saveBooking(Booking booking) {

		return bookingRepo.save(booking);

	}

}
