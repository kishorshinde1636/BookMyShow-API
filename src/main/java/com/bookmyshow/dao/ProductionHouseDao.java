package com.bookmyshow.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bookmyshow.entity.ProductionHouse;
import com.bookmyshow.repo.ProductionHouseRepo;

@Repository
public class ProductionHouseDao {

	@Autowired
	private ProductionHouseRepo houseRepo;

	public ProductionHouse saveProductionHouse(ProductionHouse houseDto) {
		return houseRepo.save(houseDto);

	}

	public ProductionHouse updateProductionHouse(long productionId, ProductionHouse house) {

		Optional<ProductionHouse> optional = houseRepo.findById(productionId);

		if (optional.isPresent()) {
			house.setProductionId(productionId);
			house.setOwner(optional.get().getOwner());
			house.setMovies(optional.get().getMovies());
			return houseRepo.save(house);
		} else {
			return null;
		}

	}

	public ProductionHouse getPoductionHouseById(long productionId) {

		Optional<ProductionHouse> optional = houseRepo.findById(productionId);

		if (optional.isPresent()) {

			return optional.get();
		} else {
			return null;
		}

	}

	public ProductionHouse deletePoductionHouseById(long productionId) {

		Optional<ProductionHouse> optional = houseRepo.findById(productionId);

		if (optional.isPresent()) {

			ProductionHouse house = optional.get();
			house.setMovies(null);
			house.setOwner(null);
			houseRepo.delete(house);
			return house;
		} else {
			return null;
		}
	}

}
