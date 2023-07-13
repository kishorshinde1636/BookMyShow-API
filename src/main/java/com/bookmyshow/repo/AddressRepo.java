package com.bookmyshow.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookmyshow.entity.Customer;

public interface AddressRepo extends JpaRepository<Customer, Long> {

}
