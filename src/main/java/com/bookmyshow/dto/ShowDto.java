package com.bookmyshow.dto;

import java.time.LocalDateTime;
import java.time.LocalTime;

import com.bookmyshow.enums.ShowStatus;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ShowDto {

	private long showId;
	private LocalDateTime showStartTime;
	private LocalDateTime showEndTime;

	// showstatus
	private ShowStatus showStatus;
	private String showLocation;

	private long movieId;
	private String MovieName;
	// genre
	private String genre;
	private LocalTime movieDuration;
	private String movieDescription;
	private String movieLanguage;

	private long screenId;
	private String screenName;
	private double classicSeatPrice;
	private double goldSeatPrice;
	private double premiumSeatPrice;

}
