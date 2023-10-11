package com.bookmyshow.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bookmyshow.dao.ScreenDao;
import com.bookmyshow.dao.TheatreDao;
import com.bookmyshow.dto.ScreenDto;
import com.bookmyshow.entity.Screen;
import com.bookmyshow.entity.Seat;
import com.bookmyshow.entity.Theatre;
import com.bookmyshow.enums.ScreenAvailability;
import com.bookmyshow.enums.ScreenStatus;
import com.bookmyshow.enums.SeatType;
import com.bookmyshow.exception.TheatreIdNotFoundException;
import com.bookmyshow.util.ResponseStructure;

@Service
public class ScreenService {

	@Autowired
	private ScreenDao screenDao;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private TheatreDao theatreDao;

	public ResponseEntity<ResponseStructure<ScreenDto>> addScreen(ScreenDto screenDto, long theatreId) {
		Theatre dbTheatre = theatreDao.getTheatreById(theatreId);

		if (dbTheatre != null) {
			Screen screen = this.modelMapper.map(screenDto, Screen.class);
//          screen variable you are having no of classic seat ,gold,premium seat
//          screen is having seat object???not present and i want to add it
//          screen is having theatre ?no but we are having theatre object themn i will set it(theatre)

			List<Seat> seats = new ArrayList<>();
			for (int a = screen.getNoOfClassicSeat(); a > 0; a--) {
				Seat seat = new Seat();
				seat.setSeatType(SeatType.CLASSIC);
				seat.setScreen(screen);
				seats.add(seat);
			}
			for (int a = screen.getNoOfPlatinumSeat(); a > 0; a--) {
				Seat seat = new Seat();
				seat.setSeatType(SeatType.PLATINUM);
				seat.setScreen(screen);
				seats.add(seat);
			}
			for (int a = screen.getNoOfGoldSeat(); a > 0; a--) {
				Seat seat = new Seat();
				seat.setSeatType(SeatType.GOLD);
				seat.setScreen(screen);
				seats.add(seat);
			}

			screen.setTheatre(dbTheatre);
			screen.setSeats(seats);
			screen.setAvailability(ScreenAvailability.NOT_ALLOTED);
			screen.setScreenStatus(ScreenStatus.AVAILABLE);

			Screen dbScreen = screenDao.addScreen(screen);

			// update the theatre

			if (dbTheatre.getScreens().isEmpty()) {
				List<Screen> list = new ArrayList<>();
				list.add(dbScreen);
				dbTheatre.setScreens(list);
				theatreDao.updateTheatre(dbTheatre, theatreId);
			} else {
				List<Screen> list = dbTheatre.getScreens();
				list.add(dbScreen);
				dbTheatre.setScreens(list);
				theatreDao.updateTheatre(dbTheatre, theatreId);

			}

			ResponseStructure<ScreenDto> responseStructure = new ResponseStructure<>();
			responseStructure.setMessage("screen save Sucessfully");
			responseStructure.setStatusCode(HttpStatus.FOUND.value());
			responseStructure.setData(this.modelMapper.map(dbScreen, ScreenDto.class));

			return new ResponseEntity<ResponseStructure<ScreenDto>>(responseStructure, HttpStatus.FOUND);
		} else {
			throw new TheatreIdNotFoundException("failed to add screen");
		}

	}

	public ResponseEntity<ResponseStructure<ScreenDto>> updateScreen(ScreenDto screenDto, long screenId) {

		Screen screen = this.modelMapper.map(screenDto, Screen.class);
		Screen dbScreen = screenDao.updateScreen(screen, screenId);

		if (dbScreen != null) {
			ResponseStructure<ScreenDto> responseStructure = new ResponseStructure<>();
			responseStructure.setMessage("screen update Sucessfully");
			responseStructure.setStatusCode(HttpStatus.FOUND.value());
			responseStructure.setData(this.modelMapper.map(dbScreen, ScreenDto.class));

			return new ResponseEntity<ResponseStructure<ScreenDto>>(responseStructure, HttpStatus.FOUND);
		} else {
			return null;
		}
	}

	public ResponseEntity<ResponseStructure<ScreenDto>> getScreenById(long screenId) {
		Screen dbScreen = screenDao.getScreenById(screenId);

		if (dbScreen != null) {
			ResponseStructure<ScreenDto> responseStructure = new ResponseStructure<>();
			responseStructure.setMessage("screen fetched Sucessfully");
			responseStructure.setStatusCode(HttpStatus.FOUND.value());
			responseStructure.setData(this.modelMapper.map(dbScreen, ScreenDto.class));

			return new ResponseEntity<ResponseStructure<ScreenDto>>(responseStructure, HttpStatus.FOUND);
		} else {
			return null;
		}
	}

	public ResponseEntity<ResponseStructure<ScreenDto>> deleteScreenById(long screenId) {

		Screen dbScreen = screenDao.deleteScreenById(screenId);
		if (dbScreen != null) {
			ResponseStructure<ScreenDto> responseStructure = new ResponseStructure<>();
			responseStructure.setMessage("screen deleted Sucessfully");
			responseStructure.setStatusCode(HttpStatus.FOUND.value());
			responseStructure.setData(this.modelMapper.map(dbScreen, ScreenDto.class));

			return new ResponseEntity<ResponseStructure<ScreenDto>>(responseStructure, HttpStatus.FOUND);
		} else {
			return null;
		}
	}

}
