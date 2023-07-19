package com.bookmyshow.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bookmyshow.entity.Theatre;
import com.bookmyshow.repo.TheatreRepo;

@Repository
public class TheatreDao {

	@Autowired
	private TheatreRepo theatreRepo;

	public Theatre saveTheatre(Theatre theatre) {
		return theatreRepo.save(theatre);
	}

	public Theatre getTheatreById(long theatreId) {

		Optional<Theatre> optional = theatreRepo.findById(theatreId);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}

	public Theatre deleteTheatreById(long theatreId) {

		Optional<Theatre> optional = theatreRepo.findById(theatreId);
		if (optional.isPresent()) {

			Theatre theatre = optional.get();
			theatre.setAddress(null);
			theatre.setAddress(null);
			theatre.setMovieShows(null);
			theatre.setScreens(null);
			theatreRepo.delete(optional.get());
			return optional.get();
		} else {
			return null;
		}

	}

	public Theatre updateTheatre(Theatre theatre, long theatreId) {
		Optional<Theatre> optional = theatreRepo.findById(theatreId);

		if (optional.isPresent()) {
			Theatre theatre2 = optional.get();
			theatre.setTheatre_Id(theatreId);
			theatre.setAddress(theatre2.getAddress());
			theatre.setMovieShows(theatre2.getMovieShows());
			theatre.setOwner(theatre2.getOwner());
			theatre.setScreens(theatre2.getScreens());

			theatreRepo.save(theatre);
			return theatre;
		} else {
			return null;
		}
	}

}
