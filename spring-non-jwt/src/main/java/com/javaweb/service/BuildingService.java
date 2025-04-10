package com.javaweb.service;

import java.util.List;
import java.util.Map;

import com.javaweb.dto.BuildingDTO;
import com.javaweb.dto.response.BuildingResponseDTO;
import com.javaweb.repository.Entity.BuildingEntity;


public interface BuildingService {
	List<BuildingResponseDTO> findAll(Map<String, Object> params, List<String> typeCode);		
	BuildingEntity createBuilding(BuildingDTO buildingDTO);
	BuildingEntity updateBuilding(BuildingDTO buildingDTO);
	String delete(List<Long> ids);
	String delete(String name);
}
