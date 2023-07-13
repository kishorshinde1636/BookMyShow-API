package com.bookmyshow.dto;

import com.bookmyshow.enums.ScreenAvailability;
import com.bookmyshow.enums.ScreenStatus;
import com.bookmyshow.enums.ScreenType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScreenDto {

	private long screenId;
	private String screenName;

	private int noOfClassicSeat;
	private int noOfPlatinumSeat;
	private int noOfGoldSeat;

	// screentype
	private ScreenType screenType;

	// screenAvabl
	private ScreenAvailability availability;
	// screenStatus
	private ScreenStatus screenStatus;

}
