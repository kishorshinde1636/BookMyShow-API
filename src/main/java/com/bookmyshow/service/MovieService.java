package com.bookmyshow.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bookmyshow.dao.MovieDao;
import com.bookmyshow.dao.ProductionHouseDao;
import com.bookmyshow.dto.MovieDto;
import com.bookmyshow.entity.Movie;
import com.bookmyshow.entity.ProductionHouse;
import com.bookmyshow.util.ResponseStructure;

@Service
public class MovieService {

	@Autowired
	private MovieDao movieDao;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ProductionHouseDao houseDao;

	public ResponseEntity<ResponseStructure<MovieDto>> saveMovie(MovieDto movieDto, long houseId) {
		ProductionHouse dbHouse = houseDao.getPoductionHouseById(houseId);

		if (dbHouse != null) {
			// add movie

			Movie movie = this.modelMapper.map(movieDto, Movie.class);

			movie.setProductionHouse(dbHouse);

			Movie dbMovie = movieDao.saveMovie(movie);

			// update production
			if (dbHouse.getMovies().isEmpty()) {
				List<Movie> list = new ArrayList<>();
				list.add(movie);
				dbHouse.setMovies(list);
				houseDao.updateProductionHouse(houseId, dbHouse);
			} else {
				List<Movie> list = dbHouse.getMovies();
				list.add(movie);
				dbHouse.setMovies(list);
				houseDao.updateProductionHouse(houseId, dbHouse);

			}
			ResponseStructure<MovieDto> responseStructure = new ResponseStructure<>();
			responseStructure.setMessage(" movie save sucessfully");
			responseStructure.setStatusCode(HttpStatus.CREATED.value());
			responseStructure.setData(this.modelMapper.map(dbMovie, MovieDto.class));

			return new ResponseEntity<ResponseStructure<MovieDto>>(responseStructure, HttpStatus.CREATED);

		} else {
			// throw new ProductionHouseIdNotFoundException("sorry failed to add movie ");
			return null;
		}
	}

	public ResponseEntity<ResponseStructure<MovieDto>> updateMovie(MovieDto movieDto, long movieId) {

		Movie movie = this.modelMapper.map(movieDto, Movie.class);

		Movie dbMovie = movieDao.updateMovie(movie, movieId);

		if (dbMovie != null) {
			ResponseStructure<MovieDto> responseStructure = new ResponseStructure<>();
			responseStructure.setMessage(" movie updated sucessfully");
			responseStructure.setStatusCode(HttpStatus.CREATED.value());
			responseStructure.setData(this.modelMapper.map(dbMovie, MovieDto.class));

			return new ResponseEntity<ResponseStructure<MovieDto>>(responseStructure, HttpStatus.CREATED);
		} else {
			return null;
			// MovieIdNotFoundException
		}
	}

	public ResponseEntity<ResponseStructure<MovieDto>> getMovieById(long movieId) {
		Movie dbMovie = movieDao.getMovieById(movieId);
		if (dbMovie != null) {
			ResponseStructure<MovieDto> responseStructure = new ResponseStructure<>();
			responseStructure.setMessage("movie fetched  sucessfully");
			responseStructure.setStatusCode(HttpStatus.CREATED.value());
			responseStructure.setData(this.modelMapper.map(dbMovie, MovieDto.class));

			return new ResponseEntity<ResponseStructure<MovieDto>>(responseStructure, HttpStatus.CREATED);
		} else {
			return null;
			// MovieIdNotFoundException
		}

	}

	public ResponseEntity<ResponseStructure<MovieDto>> deleteMovieById(long movieId) {

		Movie dbMovie = movieDao.deleteMovieById(movieId);
		if (dbMovie != null) {
			ResponseStructure<MovieDto> responseStructure = new ResponseStructure<>();
			responseStructure.setMessage("movie deleted  sucessfully");
			responseStructure.setStatusCode(HttpStatus.CREATED.value());
			responseStructure.setData(this.modelMapper.map(dbMovie, MovieDto.class));

			return new ResponseEntity<ResponseStructure<MovieDto>>(responseStructure, HttpStatus.CREATED);
		} else {
			return null;
			// MovieIdNotFoundException
		}
	}

}
