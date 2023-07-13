package com.bookmyshow.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.bookmyshow.enums.Genre;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long movieId;
	private String movieName;

	private Genre genre1;

	private Genre genre2;
	private Genre genre3;
	private LocalDate movieDuration;
	private String language;
	private String movieDescription;

	@ManyToOne
	private ProductionHouse productionHouse;

}
