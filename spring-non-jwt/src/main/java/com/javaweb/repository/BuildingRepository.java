package com.javaweb.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.repository.Entity.BuildingEntity;

public interface BuildingRepository{
	List<BuildingEntity> findAll(BuildingSearchBuilder buildingSearchBuilder);
}
