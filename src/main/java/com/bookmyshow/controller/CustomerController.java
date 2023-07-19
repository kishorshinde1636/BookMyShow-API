package com.bookmyshow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookmyshow.dto.CustomerDto;
import com.bookmyshow.entity.Customer;
import com.bookmyshow.service.CustomerService;
import com.bookmyshow.util.ResponseStructure;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@PostMapping
	public ResponseEntity<ResponseStructure<CustomerDto>> saveCustomer(@RequestBody Customer customer) {
		return customerService.saveCustomer(customer);
	}

	@GetMapping
	public ResponseEntity<ResponseStructure<CustomerDto>> getCustomerById(@RequestParam long customerId) {
		return customerService.getCustomerById(customerId);
	}

	@DeleteMapping
	public ResponseEntity<ResponseStructure<CustomerDto>> deleteCustomerById(@RequestParam long customerId) {
		return customerService.deleteCustomerById(customerId);
	}
	

	@PutMapping
	public ResponseEntity<ResponseStructure<CustomerDto>> updateCustomer(@RequestBody Customer customer,@RequestParam long customerId) {
		return customerService.updateCustomer(customer,customerId);
	}
}
