package com.bookmyshow.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
	@JoinColumn
	private Address address;

	@ManyToOne
	@JoinColumn
	private Owner owner;

	@OneToMany(mappedBy = "theatre", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Screen> screens;

	@OneToMany(mappedBy = "theatre")
	private List<MovieShow> movieShows;

}
