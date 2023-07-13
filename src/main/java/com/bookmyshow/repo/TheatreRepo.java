package com.bookmyshow.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookmyshow.entity.Theatre;

public interface TheatreRepo extends JpaRepository<Theatre, Long> {

}
