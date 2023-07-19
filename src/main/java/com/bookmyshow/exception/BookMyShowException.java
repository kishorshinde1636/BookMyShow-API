package com.bookmyshow.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.bookmyshow.util.ResponseStructure;

@RestControllerAdvice
public class BookMyShowException extends ResponseEntityExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> ownerIdNotFound(OwnerIdNotFoundException ex) {
		ResponseStructure<String> responseStructure = new ResponseStructure<>();
		responseStructure.setMessage("Id Not Found For Owner");
		responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
		responseStructure.setData(ex.getMessage());

		return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> productionIdNotFound(ProductionHouseIdNotFoundException ex) {
		ResponseStructure<String> responseStructure = new ResponseStructure<>();
		responseStructure.setMessage("Id Not Found For ProductionHouse");
		responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
		responseStructure.setData(ex.getMessage());

		return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> addressIdNotFound(AddressIdNotFoundException ex) {
		ResponseStructure<String> responseStructure = new ResponseStructure<>();
		responseStructure.setMessage("Id Not Found For Address");
		responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
		responseStructure.setData(ex.getMessage());

		return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> theatreAlreadyPresentInThisAddress(
			TheatreAlreadyPresentInThisAddressException ex) {
		ResponseStructure<String> responseStructure = new ResponseStructure<>();
		responseStructure.setMessage("Theatre Already Present In This Address");
		responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
		responseStructure.setData(ex.getMessage());

		return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.NOT_FOUND);
	}
}
