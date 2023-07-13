package com.bookmyshow.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookmyshow.entity.MovieShow;

public interface ShowRepo extends JpaRepository<MovieShow,Long> {

}
