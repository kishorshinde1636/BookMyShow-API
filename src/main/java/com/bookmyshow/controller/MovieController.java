package com.bookmyshow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookmyshow.dto.MovieDto;
import com.bookmyshow.service.MovieService;
import com.bookmyshow.util.ResponseStructure;

@RestController
@RequestMapping("/movie")
public class MovieController {

	@Autowired
	private MovieService movieService;

	@PostMapping
	public ResponseEntity<ResponseStructure<MovieDto>> saveMovie(@RequestBody MovieDto movieDto,
			@RequestParam long houseId) {
		return movieService.saveMovie(movieDto, houseId);
	}

	@PutMapping
	public ResponseEntity<ResponseStructure<MovieDto>> updateMovie(@RequestBody MovieDto movieDto,
			@RequestParam long movieId) {
		return movieService.updateMovie(movieDto, movieId);
	}

	@GetMapping
	public ResponseEntity<ResponseStructure<MovieDto>> getMovieById(@RequestParam long movieId) {
		return movieService.getMovieById(movieId);
	}

	@DeleteMapping
	public ResponseEntity<ResponseStructure<MovieDto>> deleteMovieById(@RequestParam long movieId) {
		return movieService.deleteMovieById(movieId);
	}
}
