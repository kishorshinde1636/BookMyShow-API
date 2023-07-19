package com.bookmyshow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bookmyshow.dto.ProductionHouseDto;
import com.bookmyshow.entity.ProductionHouse;
import com.bookmyshow.service.ProductionHouseService;
import com.bookmyshow.util.ResponseStructure;

@RestController
@RequestMapping("/house")
public class ProductionHouseController {

	@Autowired
	private ProductionHouseService produHouseService;

	@PostMapping
	public ResponseEntity<ResponseStructure<ProductionHouse>> saveProductionHouse(@RequestParam long ownerId,
			@RequestBody ProductionHouseDto houseDto) {
		return produHouseService.saveProductionHouse(ownerId, houseDto);
	}

	@PutMapping
	public ResponseEntity<ResponseStructure<ProductionHouse>> updateProductionHouse(
			@RequestBody ProductionHouseDto houseDto, @RequestParam long productionId) {

		return produHouseService.updateProductionHouse(houseDto, productionId);

	}

	@GetMapping
	public ResponseEntity<ResponseStructure<ProductionHouse>> getPoductionHouseById(@RequestParam long productionId) {

		return produHouseService.getPoductionHouseById(productionId);

	}

	@DeleteMapping
	public ResponseEntity<ResponseStructure<ProductionHouse>> deletePoductionHouseById(
			@RequestParam long productionId) {

		return produHouseService.deletePoductionHouseById(productionId);

	}
}
