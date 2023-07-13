package com.bookmyshow.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookmyshow.entity.Screen;

public interface ScreenRepo extends JpaRepository<Screen, Long> {

}
