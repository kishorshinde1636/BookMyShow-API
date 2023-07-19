package com.bookmyshow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookmyshow.dto.AddressDto;
import com.bookmyshow.entity.Address;
import com.bookmyshow.service.AddressService;
import com.bookmyshow.util.ResponseStructure;

@RestController
@RequestMapping("/address")
public class AddressController {

	@Autowired
	private AddressService addressService;

	@PostMapping
	public ResponseEntity<ResponseStructure<Address>> saveAddress(@RequestBody AddressDto addressDto) {
		return addressService.saveAddress(addressDto);
	}

	@GetMapping
	public ResponseEntity<ResponseStructure<Address>> getAddressById(@RequestParam long addressId) {
		return addressService.getAddressById(addressId);
	}

	@DeleteMapping
	public ResponseEntity<ResponseStructure<Address>> deleteAddressById(@RequestParam long addressId) {
		return addressService.deleteAddressById(addressId);
	}

	@PutMapping
	public ResponseEntity<ResponseStructure<Address>> updateAddress(@RequestBody AddressDto addressDto,
			@RequestParam long addressId) {
		return addressService.updateAddress(addressDto, addressId);
	}

}
