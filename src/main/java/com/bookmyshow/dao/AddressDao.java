package com.bookmyshow.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bookmyshow.dto.AddressDto;
import com.bookmyshow.entity.Address;
import com.bookmyshow.repo.AddressRepo;

@Repository
public class AddressDao {

	@Autowired
	private AddressRepo addressRepo;

	public Address saveAddress(Address address) {

		return addressRepo.save(address);

	}

	public Address getAddressById(long addressId) {
		Optional<Address> optional = addressRepo.findById(addressId);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}

	public Address deleteAddressById(long addressId) {
		Optional<Address> optional = addressRepo.findById(addressId);
		if (optional.isPresent()) {
			addressRepo.deleteById(addressId);
			return optional.get();
		} else {
			return null;
		}
	}

	public Address updateAddress(Address address, long addressId) {
		Optional<Address> optional = addressRepo.findById(addressId);
		if (optional.isPresent()) {
			address.setAddressId(addressId);
			addressRepo.save(address);
			return optional.get();
		} else {
			return null;
		}
	}

}
