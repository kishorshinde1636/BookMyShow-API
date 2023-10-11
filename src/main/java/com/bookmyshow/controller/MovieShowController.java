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

import com.bookmyshow.dto.ShowDto;
import com.bookmyshow.entity.MovieShow;
import com.bookmyshow.service.MovieShowService;
import com.bookmyshow.util.ResponseStructure;

@RestController
@RequestMapping("/show")
public class MovieShowController {

	@Autowired
	private MovieShowService movieShowService;

	@PostMapping
	public ResponseEntity<ResponseStructure<MovieShow>> addShow(@RequestBody ShowDto showDto,
			@RequestParam long theatreId) {

		return movieShowService.addShow(showDto, theatreId);
	}

	@PutMapping
	public ResponseEntity<ResponseStructure<MovieShow>> updateShow(@RequestBody ShowDto showDto,
			@RequestParam long showId) {

		return movieShowService.updateShow(showDto, showId);
	}

	@GetMapping
	public ResponseEntity<ResponseStructure<MovieShow>> getShowById(@RequestParam long showId) {

		return movieShowService.getShowById(showId);
	}

	@DeleteMapping
	public ResponseEntity<ResponseStructure<ShowDto>> deleteShowById(@RequestParam long showId) {

		return movieShowService.deleteShowById(showId);
	}
}
