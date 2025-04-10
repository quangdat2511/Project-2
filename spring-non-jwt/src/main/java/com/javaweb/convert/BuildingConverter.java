package com.javaweb.convert;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.javaweb.dto.BuildingDTO;
import com.javaweb.dto.response.BuildingResponseDTO;
import com.javaweb.myexception.DistrictNotFoundException;
import com.javaweb.repository.Entity.BuildingEntity;
import com.javaweb.repository.Entity.DistrictEntity;
import com.javaweb.repository.Entity.RentAreaEntity;

@Component
public class BuildingConverter {
	@Autowired 
	private ModelMapper modelMapper;
	@PersistenceContext
	private EntityManager entityManager;
	//DTO -> Entity
	public BuildingEntity toBuildingEntity(BuildingDTO buildingDTO) {
		BuildingEntity buildingEntityOld = null;	
		if (buildingDTO.getId() != null) {
			buildingEntityOld = entityManager.find(BuildingEntity.class, buildingDTO.getId());
		}
		BuildingEntity buildingEntityNew = modelMapper.map(buildingDTO, BuildingEntity.class);
		DistrictEntity districtEntity = entityManager.find(DistrictEntity.class, buildingDTO.getDistrictId());
		if (districtEntity == null) 
			throw new DistrictNotFoundException("District is not found in database");
		buildingEntityNew.setDistrictEntity(districtEntity);
		if (buildingEntityOld != null) {
			buildingEntityNew.setRentAreaEntities(buildingEntityOld.getRentAreaEntities());
		}
		return buildingEntityNew;
	}
	// Entity -> DTO
	public BuildingResponseDTO toBuildingSearchResponseDTO(BuildingEntity buildingEntity) {
		BuildingResponseDTO buildingResponse = modelMapper.map(buildingEntity, BuildingResponseDTO.class);
		buildingResponse.setAddress(buildingEntity.getStreet() + "," + buildingEntity.getWard() + "," + buildingEntity.getDistrictEntity().getName());
		buildingResponse.setAvailableArea(null);
        String rentAreaAsString = buildingEntity.getRentAreaEntities().stream().map(rentArea -> String.valueOf(rentArea.getValue())).collect(Collectors.joining(", "));
        buildingResponse.setRentArea(rentAreaAsString);
        return buildingResponse;
	}
}
