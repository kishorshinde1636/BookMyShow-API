package com.bookmyshow.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.bookmyshow.dao.BookingDao;
import com.bookmyshow.dao.CustomerDao;
import com.bookmyshow.dao.MovieShowDao;
import com.bookmyshow.dao.SeatDao;
import com.bookmyshow.dao.TicketDao;
import com.bookmyshow.entity.Booking;
import com.bookmyshow.entity.Customer;
import com.bookmyshow.entity.MovieShow;
import com.bookmyshow.entity.Seat;
import com.bookmyshow.entity.Ticket;
import com.bookmyshow.enums.BookingStatus;
import com.bookmyshow.enums.SeatType;
import com.bookmyshow.enums.ShowStatus;
import com.bookmyshow.enums.TicketStatus;
import com.bookmyshow.exception.TicketAlreadyCancelledException;
import com.bookmyshow.exception.TicketAlreadyExpiredException;
import com.bookmyshow.exception.TicketCanNotBeCancelled;
import com.bookmyshow.util.ResponseStructure;

@Service
public class TicketService {

	@Autowired
	private TicketDao ticketDao;

	@Autowired
	private CustomerDao customerDao;

	@Autowired
	private MovieShowDao showDao;

	@Autowired
	private SeatDao seatDao;

	@Autowired
	private BookingDao bookingDao;

	public ResponseEntity<ResponseStructure<Ticket>> saveTicket(long showId, long customerId, long seatId) {
		Customer dbCustomer = customerDao.getCustomerById(customerId);

		Ticket ticket = new Ticket();
		if (dbCustomer != null) {
			ticket.setCustomer(dbCustomer);
		} else {
			// throw new CustomerIdNotFoundException();
			return null;
		}

		MovieShow dbMovieShow = showDao.getShowById(showId);

		if (dbMovieShow != null) {
			if (dbMovieShow.getShowStatus().equals(ShowStatus.ACTIVE)) {
				ticket.setMovieShow(dbMovieShow);
			} else {
				return null;

				// throw new ShowIsNotActiveException()
			}
		} else {
			// throw new showIdNotFoundException()
			return null;
		}

		List<Booking> bookings = new ArrayList<>();
		List<Seat> seats = new ArrayList<>();
		double totalPrice = 0;

		Seat dbSeat = seatDao.getSeatById(seatId);

		if (dbSeat != null) {

			Booking booking = new Booking();
			booking.setSeatId(dbSeat.getSeatId());
			booking.setSeatType(dbSeat.getSeatType());
			booking.setBookingStatus(BookingStatus.ACTIVE);
			booking.setBookingFromTime(dbMovieShow.getShowStartTime());
			booking.setBookingTillTime(dbMovieShow.getShowEndTime());

			SeatType seatType = booking.getSeatType();

			switch (seatType) {
			case CLASSIC:
				booking.setSeatPrice(dbMovieShow.getClassicSeatPrice());
				totalPrice += dbMovieShow.getClassicSeatPrice();
				break;

			case GOLD:
				booking.setSeatPrice(dbMovieShow.getGoldSeatPrice());
				totalPrice += dbMovieShow.getGoldSeatPrice();
				break;

			case PLATINUM:
				booking.setSeatPrice(dbMovieShow.getPremiumSeatPrice());
				totalPrice += dbMovieShow.getPremiumSeatPrice();
				break;
			}
			bookings.add(booking);
			seats.add(dbSeat);

			bookingDao.saveBooking(booking);

			ticket.setBookings(bookings);
			ticket.setTotalPrice(totalPrice);
			ticket.setStatus(TicketStatus.ACTIVE);
			Ticket dbTicket = ticketDao.saveTicket(ticket);

			ResponseStructure<Ticket> responseStructure = new ResponseStructure<>();
			responseStructure.setMessage("Ticket Booked successfully");
			responseStructure.setStatusCode(HttpStatus.CREATED.value());
			responseStructure.setData(dbTicket);

			return new ResponseEntity<ResponseStructure<Ticket>>(responseStructure, HttpStatus.CREATED);

		} else {
			return null;
			// throw new SeatIdNotFoundException()
		}

	}

	public ResponseEntity<ResponseStructure<Ticket>> cancelTicket(long ticketId) {

		Ticket dbTicket = ticketDao.getTicketById(ticketId);

		if (dbTicket != null) {
			if (dbTicket.getMovieShow().getShowStatus().equals(ShowStatus.ON_GOING)) {
				throw new TicketCanNotBeCancelled("sorry failed to cancel ticekt");
			} else if (dbTicket.getStatus().equals(TicketStatus.CANCELLED)) {

				throw new TicketAlreadyCancelledException("Sorry failed to cancel Ticket");
			} else if (dbTicket.getStatus().equals(TicketStatus.EXPIRED)) {
				throw new TicketAlreadyExpiredException("Sorry failed to cancel Ticket");
			} else {
				List<Booking> bookings = dbTicket.getBookings();
				for (Booking b : bookings) {
					b.setBookingStatus(BookingStatus.CANCELLED);
					bookingDao.saveBooking(b);
				}
				dbTicket.setStatus(TicketStatus.CANCELLED);
				ticketDao.saveTicket(dbTicket);

				ResponseStructure<Ticket> responseStructure = new ResponseStructure<>();
				responseStructure.setMessage("Ticket cancelled successfully");
				responseStructure.setStatusCode(HttpStatus.CREATED.value());
				responseStructure.setData(dbTicket);

				return new ResponseEntity<ResponseStructure<Ticket>>(responseStructure, HttpStatus.CREATED);

			}

		} else {
			return null;
			// throw new TicketIdNotFoundException()
		}
	}
}
