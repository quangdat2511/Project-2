package com.javaweb.service.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.builder.BuildingSearchBuilderConverter;
import com.javaweb.convert.BuildingConverter;
import com.javaweb.dto.BuildingDTO;
import com.javaweb.dto.response.BuildingResponseDTO;
import com.javaweb.respository.BuildingRepository;
import com.javaweb.respository.Entity.BuildingEntity;
import com.javaweb.respository.Entity.RentAreaEntity;
import com.javaweb.service.BuildingService;

@Service  
@Transactional
public class BuildingServiceImpl implements BuildingService{
	@Autowired	
	private BuildingRepository buildingRepository;
	@Autowired
	private BuildingConverter buildingConverter;
	@Autowired
	private BuildingSearchBuilderConverter buildingSearchBuilderConverter;
	@PersistenceContext
	private EntityManager entityManager;
		
	@Override
	public List<BuildingResponseDTO> findAll(Map<String, Object> params, List<String> typeCode) {
		BuildingSearchBuilder buildingSearchBuilder = buildingSearchBuilderConverter.toBuildingSearchBuilder(params, typeCode);
		List<BuildingEntity> buildingEntities = buildingRepository.findAll(buildingSearchBuilder);
		//filter
		List<BuildingResponseDTO> results = new ArrayList<>();
		for (BuildingEntity buildingEntity: buildingEntities) {
			BuildingResponseDTO buildingResponse = buildingConverter.toBuildingSearchResponseDTO(buildingEntity);
			results.add(buildingResponse);
		}	
		return results;			
	}
	@Override
	public String delete(List<Long> ids) {
		for (Long id: ids) {
			BuildingEntity buildingEntity = entityManager.find(BuildingEntity.class, id);
			if (buildingEntity != null) {
				List<RentAreaEntity> rentAreaEntities = buildingEntity.getRentAreaEntities();
				for (RentAreaEntity rentAreaEntity: rentAreaEntities) {
					entityManager.remove(rentAreaEntity);
				}
				entityManager.remove(buildingEntity); 
			}
		}
		return "Success";
	}
	@Override
	public BuildingEntity createBuilding(BuildingDTO buildingDTO) {
		BuildingEntity buildingEntity = buildingConverter.toBuildingEntity(buildingDTO);
		entityManager.persist(buildingEntity);
		
		for (Long rentArea: buildingDTO.getRentArea()) {
			RentAreaEntity rentAreaEntity = new RentAreaEntity();
			rentAreaEntity.setValue(rentArea);
			rentAreaEntity.setBuildingEntity(buildingEntity);
			entityManager.persist(rentAreaEntity);
		}
		return buildingEntity;
	}
	@Override
	public BuildingEntity updateBuilding(BuildingDTO buildingDTO) {
		BuildingEntity buildingEntity = buildingConverter.toBuildingEntity(buildingDTO);
		entityManager.merge(buildingEntity);
		if (buildingEntity.getRentAreaEntities() != null) {
			for (RentAreaEntity rentAreaEntity: buildingEntity.getRentAreaEntities()) {
				entityManager.remove(rentAreaEntity);
			}			
		}

		for (Long rentArea: buildingDTO.getRentArea()) {
			RentAreaEntity rentAreaEntity = new RentAreaEntity();
			rentAreaEntity.setValue(rentArea);
			rentAreaEntity.setBuildingEntity(buildingEntity);
			entityManager.persist(rentAreaEntity);
		}
		return buildingEntity;
	}
}
	