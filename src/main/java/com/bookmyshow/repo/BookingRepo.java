package com.bookmyshow.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookmyshow.entity.Booking;

public interface BookingRepo extends JpaRepository<Booking, Long> {

}
