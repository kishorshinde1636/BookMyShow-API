package com.bookmyshow.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductionHouseDto {

	private long productionId;
	private String productionName;
	private LocalDate establishment;
}
