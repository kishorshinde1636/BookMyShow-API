package com.bookmyshow.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bookmyshow.entity.Owner;
import com.bookmyshow.repo.OwnerRepo;

@Repository
public class OwnerDao {

	@Autowired
	private OwnerRepo ownerRepo;

	public Owner saveOwner(Owner owner) {

		return ownerRepo.save(owner);
	}

	public Owner findOwnerById(long ownerId) {

		Optional<Owner> owner = ownerRepo.findById(ownerId);
		if (owner.isPresent()) {
			return owner.get();
		}
		return null;
	}

	public Owner deleteOwnerById(long ownerId) {

		Optional<Owner> owner = ownerRepo.findById(ownerId);
		if (owner.isPresent()) {
			ownerRepo.deleteById(ownerId);
			return owner.get();
		}
		return null;

	}

	public Owner updateOwnerById(long ownerId, Owner owner) {

		Optional<Owner> optional = ownerRepo.findById(ownerId);
		if (optional.isPresent()) {
			owner.setOwnerId(ownerId);
			ownerRepo.save(owner);

			return owner;
		}
		return null;

	}

}
