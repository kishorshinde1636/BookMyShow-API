package com.bookmyshow.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Theatre {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long theatre_Id;
	private String theatreName;
	
	@OneToOne
	private Address address;
	
	@ManyToOne
	private Owner owner;
	
	@OneToMany
	private List<Screen> screens;
	
	@OneToMany
	private List<MovieShow> movieShows;

}
