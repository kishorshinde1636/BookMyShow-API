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

import com.bookmyshow.dto.TheatreDto;
import com.bookmyshow.entity.Theatre;
import com.bookmyshow.service.TheatreService;
import com.bookmyshow.util.ResponseStructure;

@RestController
@RequestMapping("/theatre")
public class TheatreController {

	@Autowired
	private TheatreService theatreService;

	@PostMapping
	public ResponseEntity<ResponseStructure<Theatre>> saveTheatre(@RequestParam long ownerId,
			@RequestParam long addressId, @RequestBody TheatreDto theatreDto) {
		return theatreService.saveTheatre(ownerId, addressId, theatreDto);
	}

	@GetMapping
	public ResponseEntity<ResponseStructure<Theatre>> getTheatreById(@RequestParam long theatreId) {
		return theatreService.getTheatreById(theatreId);
	}

	@DeleteMapping
	public ResponseEntity<ResponseStructure<Theatre>> deleteTheatreById(@RequestParam long theatreId) {
		return theatreService.deleteTheatreById(theatreId);
	}

	@PutMapping
	public ResponseEntity<ResponseStructure<Theatre>> updateTheatre(@RequestParam long theatreId,
			@RequestBody TheatreDto theatreDto) {
		return theatreService.updateTheatre(theatreId, theatreDto);
	}
}
