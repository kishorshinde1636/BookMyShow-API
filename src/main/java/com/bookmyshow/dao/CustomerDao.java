package com.bookmyshow.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bookmyshow.entity.Customer;
import com.bookmyshow.repo.CustomerRepo;

@Repository
public class CustomerDao {

	@Autowired
	private CustomerRepo customerRepo;

	public Customer saveCustomer(Customer customer) {

		return customerRepo.save(customer);

	}

	public Customer getCustomerById(long customerId) {
		Optional<Customer> optional = customerRepo.findById(customerId);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}

	public Customer deleteCustomerById(long customerId) {
		Optional<Customer> optional = customerRepo.findById(customerId);
		if (optional.isPresent()) {
			customerRepo.deleteById(customerId);
			return optional.get();
		} else {
			return null;
		}
	}

	public Customer updateCustomer(Customer customer, long customerId) {
		Optional<Customer> optional = customerRepo.findById(customerId);
		if (optional.isPresent()) {
			customer.setCustomerId(customerId);
			customerRepo.save(customer);
			return optional.get();
		} else {
			return null;
		}
	}
}
