package com.bookmyshow.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bookmyshow.entity.MovieShow;
import com.bookmyshow.repo.ShowRepo;

@Repository
public class MovieShowDao {

	@Autowired
	private ShowRepo showRepo;

	public MovieShow updateShow(MovieShow movieShow, long showId) {
		Optional<MovieShow> optional = showRepo.findById(showId);

		if (optional.isPresent()) {
			movieShow.setShow_Id(showId);
			movieShow.setTheatre(optional.get().getTheatre());
			return showRepo.save(movieShow);
		} else {
			return null;
		}

	}

	public MovieShow getShowById(long showId) {
		Optional<MovieShow> optional = showRepo.findById(showId);

		if (optional.isPresent()) {

			return optional.get();
		} else {
			return null;
		}
	}

	public MovieShow deleteShowById(long showId) {
		Optional<MovieShow> optional = showRepo.findById(showId);

		if (optional.isPresent()) {
			MovieShow movieShow = optional.get();
			movieShow.setTheatre(null);
			showRepo.deleteById(showId);
			return optional.get();
		} else {
			return null;
		}
	}

	public MovieShow saveShow(MovieShow movieShow) {
		return showRepo.save(movieShow);

	}

}
