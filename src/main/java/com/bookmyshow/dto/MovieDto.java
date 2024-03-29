package com.bookmyshow.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.bookmyshow.enums.Genre;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MovieDto {

	private long movieId;
	private String movieName;

	// genress
	private Genre genre1;

	private Genre genre2;
	private Genre genre3;
	private LocalTime movieDuration;
	private String language;
	private String movieDescription;
}
