package com.bookmyshow.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CustomerDto {

	private long customerId;
	private String customerName;
	private long customerPhone;
	private String customerEmail;

}
