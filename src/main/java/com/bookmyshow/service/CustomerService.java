package com.bookmyshow.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bookmyshow.dao.CustomerDao;
import com.bookmyshow.dto.CustomerDto;
import com.bookmyshow.entity.Customer;
import com.bookmyshow.util.ResponseStructure;

@Service
public class CustomerService {

	@Autowired
	private CustomerDao customerDao;

	@Autowired
	private ModelMapper modelMapper;

	public ResponseEntity<ResponseStructure<CustomerDto>> saveCustomer(Customer customer) {

		Customer dbCustomer = customerDao.saveCustomer(customer);
		if (dbCustomer != null) {
			CustomerDto customerDto = this.modelMapper.map(customer, CustomerDto.class);

			ResponseStructure<CustomerDto> responseStructure = new ResponseStructure<>();
			responseStructure.setMessage("save Customer sucessfully");
			responseStructure.setStatusCode(HttpStatus.CREATED.value());
			responseStructure.setData(customerDto);

			return new ResponseEntity<ResponseStructure<CustomerDto>>(responseStructure, HttpStatus.CREATED);

		} else {
			return null;
		}
	}

	public ResponseEntity<ResponseStructure<CustomerDto>> getCustomerById(long customerId) {

		Customer dbCustomer = customerDao.getCustomerById(customerId);
		if (dbCustomer != null) {
			CustomerDto customerDto = this.modelMapper.map(dbCustomer, CustomerDto.class);

			ResponseStructure<CustomerDto> responseStructure = new ResponseStructure<>();
			responseStructure.setMessage("Customer fetched sucessfully");
			responseStructure.setStatusCode(HttpStatus.FOUND.value());
			responseStructure.setData(customerDto);

			return new ResponseEntity<ResponseStructure<CustomerDto>>(responseStructure, HttpStatus.FOUND);

		} else {
			return null;
		}
	}

	public ResponseEntity<ResponseStructure<CustomerDto>> deleteCustomerById(long customerId) {
		Customer dbCustomer = customerDao.deleteCustomerById(customerId);
		if (dbCustomer != null) {
			CustomerDto customerDto = this.modelMapper.map(dbCustomer, CustomerDto.class);

			ResponseStructure<CustomerDto> responseStructure = new ResponseStructure<>();
			responseStructure.setMessage("Customer deleted sucessfully");
			responseStructure.setStatusCode(HttpStatus.FOUND.value());
			responseStructure.setData(customerDto);

			return new ResponseEntity<ResponseStructure<CustomerDto>>(responseStructure, HttpStatus.FOUND);

		} else {
			return null;
		}
	}

	public ResponseEntity<ResponseStructure<CustomerDto>> updateCustomer(Customer customer, long customerId) {
	
		Customer dbCustomer = customerDao.updateCustomer(customer,customerId);
		if (dbCustomer != null) {
			CustomerDto customerDto = this.modelMapper.map(dbCustomer, CustomerDto.class);

			ResponseStructure<CustomerDto> responseStructure = new ResponseStructure<>();
			responseStructure.setMessage("Customer updated sucessfully");
			responseStructure.setStatusCode(HttpStatus.FOUND.value());
			responseStructure.setData(customerDto);

			return new ResponseEntity<ResponseStructure<CustomerDto>>(responseStructure, HttpStatus.FOUND);

		} else {
			return null;
		}
	}

}
