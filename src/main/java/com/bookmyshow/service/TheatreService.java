package com.bookmyshow.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bookmyshow.dao.AddressDao;
import com.bookmyshow.dao.OwnerDao;
import com.bookmyshow.dao.TheatreDao;
import com.bookmyshow.dto.TheatreDto;
import com.bookmyshow.entity.Address;
import com.bookmyshow.entity.Owner;
import com.bookmyshow.entity.Theatre;
import com.bookmyshow.exception.AddressIdNotFoundException;
import com.bookmyshow.exception.OwnerIdNotFoundException;
import com.bookmyshow.exception.TheatreAlreadyPresentInThisAddressException;
import com.bookmyshow.util.ResponseStructure;

@Service
public class TheatreService {

	@Autowired
	private TheatreDao theatreDao;

	@Autowired
	private OwnerDao ownerDao;

	@Autowired
	private AddressDao addressDao;

	@Autowired
	private ModelMapper modelMapper;

	public ResponseEntity<ResponseStructure<Theatre>> saveTheatre(long ownerId, long addressId, TheatreDto theatreDto) {

		Owner owner = ownerDao.findOwnerById(ownerId);

		if (owner != null) {

			Address address = addressDao.getAddressById(addressId);

			if (address != null) {
				Theatre addressTheatre = address.getTheatre();

				if (addressTheatre != null) {
					throw new TheatreAlreadyPresentInThisAddressException("address is mapped to other address");
				}

				Theatre theatre = this.modelMapper.map(theatreDto, Theatre.class);

				theatre.setAddress(address);
				theatre.setOwner(owner);

				if (owner.getTheatres().isEmpty()) {
					List<Theatre> list = new ArrayList<>();
					list.add(theatre);
					owner.setTheatres(list);
				} else {
					List<Theatre> list = owner.getTheatres();
					list.add(theatre);
					owner.setTheatres(list);
				}
				address.setTheatre(theatre);

				Theatre dbTheatre = theatreDao.saveTheatre(theatre);

				ResponseStructure<Theatre> responseStructure = new ResponseStructure<>();
				responseStructure.setMessage("Theatre added successfuly");
				responseStructure.setStatusCode(HttpStatus.CREATED.value());
				responseStructure.setData(dbTheatre);

				return new ResponseEntity<ResponseStructure<Theatre>>(responseStructure, HttpStatus.CREATED);
			} else {

				throw new AddressIdNotFoundException("sorry failed to add theatre");
			}
		} else {
			throw new OwnerIdNotFoundException("sorry failed to add theatre");
		}
	}

	public ResponseEntity<ResponseStructure<Theatre>> getTheatreById(long theatreId) {
		Theatre dbTheatre = theatreDao.getTheatreById(theatreId);
		if (dbTheatre != null) {
			ResponseStructure<Theatre> responseStructure = new ResponseStructure<>();
			responseStructure.setMessage("Theatre fetched successfuly");
			responseStructure.setStatusCode(HttpStatus.FOUND.value());
			responseStructure.setData(dbTheatre);

			return new ResponseEntity<ResponseStructure<Theatre>>(responseStructure, HttpStatus.FOUND);
		} else {
			return null;
		}
	}

	public ResponseEntity<ResponseStructure<Theatre>> deleteTheatreById(long theatreId) {
		Theatre dbTheatre = theatreDao.deleteTheatreById(theatreId);

		if (dbTheatre != null) {
			ResponseStructure<Theatre> responseStructure = new ResponseStructure<>();
			responseStructure.setMessage("Theatre deleted successfuly");
			responseStructure.setStatusCode(HttpStatus.FOUND.value());
			responseStructure.setData(dbTheatre);

			return new ResponseEntity<ResponseStructure<Theatre>>(responseStructure, HttpStatus.FOUND);
		} else {
			return null;
		}
	}

	public ResponseEntity<ResponseStructure<Theatre>> updateTheatre(long theatreId, TheatreDto theatreDto) {
		Theatre theatre = this.modelMapper.map(theatreDto, Theatre.class);
		Theatre dbTheatre = theatreDao.updateTheatre(theatre, theatreId);

		if (dbTheatre != null) {
			ResponseStructure<Theatre> responseStructure = new ResponseStructure<>();
			responseStructure.setMessage("Theatre updated successfuly");
			responseStructure.setStatusCode(HttpStatus.FOUND.value());
			responseStructure.setData(dbTheatre);

			return new ResponseEntity<ResponseStructure<Theatre>>(responseStructure, HttpStatus.FOUND);
		} else {
			return null;
		}
	}
}
