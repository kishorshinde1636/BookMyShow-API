package com.bookmyshow.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bookmyshow.dao.AddressDao;
import com.bookmyshow.dto.AddressDto;
import com.bookmyshow.entity.Address;
import com.bookmyshow.util.ResponseStructure;

@Service
public class AddressService {

	@Autowired
	private AddressDao addressDao;

	@Autowired
	private ModelMapper modelMapper;

	public ResponseEntity<ResponseStructure<Address>> saveAddress(AddressDto addressDto) {

		Address address = this.modelMapper.map(addressDto, Address.class);
		Address dbAddress = addressDao.saveAddress(address);

		ResponseStructure<Address> responseStructure = new ResponseStructure<>();
		responseStructure.setMessage("address save sucessfully");
		responseStructure.setStatusCode(HttpStatus.CREATED.value());
		responseStructure.setData(dbAddress);

		return new ResponseEntity<ResponseStructure<Address>>(responseStructure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Address>> getAddressById(long addressId) {
		Address dbAddress = addressDao.getAddressById(addressId);
		if (dbAddress != null) {
			Address address = this.modelMapper.map(dbAddress, Address.class);
			ResponseStructure<Address> responseStructure = new ResponseStructure<>();
			responseStructure.setMessage("address fetched sucessfully");
			responseStructure.setStatusCode(HttpStatus.FOUND.value());
			responseStructure.setData(dbAddress);

			return new ResponseEntity<ResponseStructure<Address>>(responseStructure, HttpStatus.FOUND);
		} else {
			return null;
		}
	}

	public ResponseEntity<ResponseStructure<Address>> deleteAddressById(long addressId) {
		Address dbAddress = addressDao.deleteAddressById(addressId);
		if (dbAddress != null) {
			Address address = this.modelMapper.map(dbAddress, Address.class);
			ResponseStructure<Address> responseStructure = new ResponseStructure<>();
			responseStructure.setMessage("address deleted sucessfully");
			responseStructure.setStatusCode(HttpStatus.FOUND.value());
			responseStructure.setData(dbAddress);

			return new ResponseEntity<ResponseStructure<Address>>(responseStructure, HttpStatus.FOUND);
		} else {
			return null;
		}
	}

	public ResponseEntity<ResponseStructure<Address>> updateAddress(AddressDto addressDto, long addressId) {

		Address address = this.modelMapper.map(addressDto, Address.class);
		Address dbAddress = addressDao.updateAddress(address, addressId);
		if (dbAddress != null) {

			ResponseStructure<Address> responseStructure = new ResponseStructure<>();
			responseStructure.setMessage("address updated sucessfully");
			responseStructure.setStatusCode(HttpStatus.FOUND.value());
			responseStructure.setData(dbAddress);

			return new ResponseEntity<ResponseStructure<Address>>(responseStructure, HttpStatus.FOUND);
		} else {
			return null;
		}
	}

}
