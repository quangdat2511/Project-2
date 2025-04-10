package com.javaweb.respository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.respository.Entity.BuildingEntity;

public interface BuildingRepository extends JpaRepository<BuildingEntity, Long>{
	public List<BuildingEntity> findAll(BuildingSearchBuilder buildingSearchBuilder);
	
}
