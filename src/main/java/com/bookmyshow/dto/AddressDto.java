package com.bookmyshow.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddressDto {

	private long addressId;
	private int flatNo;
	private String area;
	private String landmark;
	private String city;
	private String state;
	private String country;
	private long pincode;


}
