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

import com.bookmyshow.dto.ScreenDto;
import com.bookmyshow.entity.Screen;
import com.bookmyshow.service.ScreenService;
import com.bookmyshow.util.ResponseStructure;

@RestController
@RequestMapping("/screen")
public class ScreenController {

	@Autowired
	private ScreenService screenService;

	@PostMapping
	public ResponseEntity<ResponseStructure<ScreenDto>> addScreen(@RequestBody ScreenDto screenDto,
			@RequestParam long theatreId) {
		return screenService.addScreen(screenDto, theatreId);
	}

	@PutMapping
	public ResponseEntity<ResponseStructure<ScreenDto>> updateScreen(@RequestBody ScreenDto screenDto,
			@RequestParam long screenId) {
		return screenService.updateScreen(screenDto, screenId);
	}

	@GetMapping
	public ResponseEntity<ResponseStructure<ScreenDto>> getScreenById(@RequestParam long screenId) {
		return screenService.getScreenById(screenId);
	}

	@DeleteMapping
	public ResponseEntity<ResponseStructure<ScreenDto>> deleteScreenById(@RequestParam long screenId) {
		return screenService.deleteScreenById(screenId);
	}
}
