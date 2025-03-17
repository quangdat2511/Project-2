package com.javaweb.respository;

import java.util.List;
import java.util.Map;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.respository.Entity.BuildingEntity;

public interface BuildingRepository {
	public List<BuildingEntity> findAll(BuildingSearchBuilder buildingSearchBuilder);
	
}
