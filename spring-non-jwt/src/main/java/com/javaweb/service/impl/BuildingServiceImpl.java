package com.javaweb.service.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.builder.BuildingSearchBuilderConverter;
import com.javaweb.convert.BuildingConverter;
import com.javaweb.dto.response.BuildingResponseDTO;
import com.javaweb.respository.BuildingRepository;
import com.javaweb.respository.DistrictRepository;
import com.javaweb.respository.RentAreaRespository;
import com.javaweb.respository.Entity.BuildingEntity;
import com.javaweb.respository.Entity.DistrictEntity;
import com.javaweb.respository.Entity.RentAreaEntity;
import com.javaweb.service.BuildingService;

@Service  
public class BuildingServiceImpl implements BuildingService{
	@Autowired	
	private BuildingRepository buildingRepository;
	@Autowired
	private BuildingConverter buildingConverter;
	@Autowired
	private BuildingSearchBuilderConverter buildingSearchBuilderConverter;
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
	// TODO Auto-generated method stub
	return null;
}
}
	