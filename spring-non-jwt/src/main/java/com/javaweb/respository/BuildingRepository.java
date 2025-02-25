package com.javaweb.respository;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.javaweb.respository.Entity.BuildingEntity;

public interface BuildingRepository {
	public List<BuildingEntity> findAll(Map<String, Object> params, List<String> typeCode);
	
}
