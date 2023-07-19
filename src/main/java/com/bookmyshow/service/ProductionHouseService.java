package com.bookmyshow.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bookmyshow.dao.OwnerDao;
import com.bookmyshow.dao.ProductionHouseDao;
import com.bookmyshow.dto.OwnerDto;
import com.bookmyshow.dto.ProductionHouseDto;
import com.bookmyshow.entity.Owner;
import com.bookmyshow.entity.ProductionHouse;
import com.bookmyshow.exception.OwnerIdNotFoundException;
import com.bookmyshow.exception.ProductionHouseIdNotFoundException;
import com.bookmyshow.util.ResponseStructure;

@Service
public class ProductionHouseService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private OwnerDao ownerDao;

	@Autowired
	private ProductionHouseDao productionHouseDao;

	public ResponseEntity<ResponseStructure<ProductionHouse>> saveProductionHouse(long ownerId,
			ProductionHouseDto houseDto) {

		Owner dbOwner = ownerDao.findOwnerById(ownerId);

		if (dbOwner != null) {
			ProductionHouse productionHouse = this.modelMapper.map(houseDto, ProductionHouse.class);

			
			if(dbOwner.getHouses().isEmpty())
			{
				List<ProductionHouse> list=new ArrayList<>();
				list.add(productionHouse);
				dbOwner.setHouses(list);
			}
			else {
				List<ProductionHouse> list=dbOwner.getHouses();
				list.add(productionHouse);
				dbOwner.setHouses(list);
			}
			productionHouse.setOwner(dbOwner);
			
			ProductionHouse house = productionHouseDao.saveProductionHouse(productionHouse);
			house.setOwner(dbOwner);
			ResponseStructure<ProductionHouse> responseStructure = new ResponseStructure<ProductionHouse>();
			responseStructure.setMessage(" Productionhouse added sucessfully");
			responseStructure.setStatusCode(HttpStatus.CREATED.value());
			responseStructure.setData(house);

			return new ResponseEntity<ResponseStructure<ProductionHouse>>(responseStructure, HttpStatus.CREATED);
		} else {
			throw new OwnerIdNotFoundException("failed to find Owner with requesred Id");
		}

	}

	public ResponseEntity<ResponseStructure<ProductionHouse>> updateProductionHouse(ProductionHouseDto houseDto,
			long productionId) {

		ProductionHouse house = this.modelMapper.map(houseDto, ProductionHouse.class);

		ProductionHouse dbHouse = productionHouseDao.updateProductionHouse(productionId, house);

		if (dbHouse != null) {
			ResponseStructure<ProductionHouse> responseStructure = new ResponseStructure<ProductionHouse>();
			responseStructure.setMessage(" Productionhouse updated sucessfully");
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setData(house);

			return new ResponseEntity<ResponseStructure<ProductionHouse>>(responseStructure, HttpStatus.OK);
		} else {
			throw new ProductionHouseIdNotFoundException("failed to update with requested Id");

		}

	}

	public ResponseEntity<ResponseStructure<ProductionHouse>> getPoductionHouseById(long productionId) {

		ProductionHouse dbHouse = productionHouseDao.getPoductionHouseById(productionId);

		if (dbHouse != null) {
			ResponseStructure<ProductionHouse> responseStructure = new ResponseStructure<ProductionHouse>();
			responseStructure.setMessage(" Productionhouse fetched sucessfully");
			responseStructure.setStatusCode(HttpStatus.FOUND.value());
			responseStructure.setData(dbHouse);

			return new ResponseEntity<ResponseStructure<ProductionHouse>>(responseStructure, HttpStatus.FOUND);
		} else {
			throw new ProductionHouseIdNotFoundException("failed to update with requested Id");

		}

	}

	public ResponseEntity<ResponseStructure<ProductionHouse>> deletePoductionHouseById(long productionId) {
		
		
		ProductionHouse dbHouse = productionHouseDao.deletePoductionHouseById(productionId);

		if (dbHouse != null) {
			ResponseStructure<ProductionHouse> responseStructure = new ResponseStructure<ProductionHouse>();
			responseStructure.setMessage(" Productionhouse Deleted sucessfully");
			responseStructure.setStatusCode(HttpStatus.FOUND.value());
			responseStructure.setData(dbHouse);

			return new ResponseEntity<ResponseStructure<ProductionHouse>>(responseStructure, HttpStatus.FOUND);
		} else {
			throw new ProductionHouseIdNotFoundException("failed to delete with requested Id");

		}

	}

}
