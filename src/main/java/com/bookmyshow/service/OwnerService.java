package com.bookmyshow.service;

import javax.websocket.server.ServerEndpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bookmyshow.dao.OwnerDao;
import com.bookmyshow.dto.OwnerDto;
import com.bookmyshow.entity.Owner;
import com.bookmyshow.util.ResponseStructure;

@Service
public class OwnerService {

	@Autowired
	private OwnerDao ownerDao;

	public ResponseEntity<ResponseStructure<OwnerDto>> saveOwner(Owner owner) {

		Owner dbowner = ownerDao.saveOwner(owner);
		OwnerDto dto = new OwnerDto();
		dto.setOwnerId(owner.getOwnerId());
		dto.setOwnername(owner.getOwnername());
		dto.setOwnerEmail(owner.getOwnerEmail());
		dto.setOwnerPhoneNumber(owner.getOwnerPhoneNumber());

		ResponseStructure<OwnerDto> responseStructure = new ResponseStructure<>();
		responseStructure.setMessage("owner saved successfully");
		responseStructure.setStatusCode(HttpStatus.CREATED.value());
		responseStructure.setData(dto);

		return new ResponseEntity<ResponseStructure<OwnerDto>>(responseStructure, HttpStatus.CREATED);

	}

	public ResponseEntity<ResponseStructure<OwnerDto>> findOwnerById(long ownerId) {

		Owner dbOwner = ownerDao.findOwnerById(ownerId);

		if (dbOwner != null) {
			OwnerDto dto = new OwnerDto();
			dto.setOwnerId(dbOwner.getOwnerId());
			dto.setOwnername(dbOwner.getOwnername());
			dto.setOwnerEmail(dbOwner.getOwnerEmail());
			dto.setOwnerPhoneNumber(dbOwner.getOwnerPhoneNumber());

			ResponseStructure<OwnerDto> responseStructure = new ResponseStructure<>();
			responseStructure.setMessage("owner fetched successfully");
			responseStructure.setStatusCode(HttpStatus.FOUND.value());
			responseStructure.setData(dto);

			return new ResponseEntity<ResponseStructure<OwnerDto>>(responseStructure, HttpStatus.FOUND);

		} else {
			return null;
		}
	}

}
