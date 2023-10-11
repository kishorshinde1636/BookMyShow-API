package com.bookmyshow.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddressDto {

	private long addressId;

	
	private int flatNo;
	@NotNull(message = "FlatNo can't be Null")
	@NotBlank(message = "FlatNo can't be Null")
	private String area;
	private String landmark;
	private String city;
	private String state;
	private String country;
	private long pincode;

}
