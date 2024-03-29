package com.bookmyshow.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Owner {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long ownerId;
	private String ownername;
	private long ownerPhoneNumber;
	private String ownerEmail;
	private String ownerPassword;

	@OneToMany(mappedBy = "owner")
	@JsonIgnore
	private List<ProductionHouse> houses;
	
	@OneToMany(mappedBy = "owner")
	@JsonIgnore
	private List<Theatre> theatres;
}
