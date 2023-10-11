package com.bookmyshow.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bookmyshow.entity.Movie;
import com.bookmyshow.repo.MovieRepo;

@Repository
public class MovieDao {

	@Autowired
	private MovieRepo movieRepo;

	public Movie updateMovie(Movie movie, long movieId) {

		Optional<Movie> optional = movieRepo.findById(movieId);
		if (optional.isPresent()) {
			movie.setMovieId(movieId);
			movie.setProductionHouse(optional.get().getProductionHouse());

			return movieRepo.save(movie);
		} else {
			return null;
		}
	}

	public Movie getMovieById(long movieId) {

		Optional<Movie> optional = movieRepo.findById(movieId);
		if (optional.isPresent()) {

			return optional.get();
		} else {
			return null;
		}

	}

	public Movie deleteMovieById(long movieId) {
		Optional<Movie> optional = movieRepo.findById(movieId);
		if (optional.isPresent()) {
			Movie movie = optional.get();
			movie.setProductionHouse(null);
			return optional.get();
		} else {
			return null;
		}
	}

	public Movie saveMovie(Movie movie) {
		return movieRepo.save(movie);
	}
}
