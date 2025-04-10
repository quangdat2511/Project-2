package com.javaweb.repository;

import java.util.List;
import java.util.Map;

import com.javaweb.repository.Entity.RentAreaEntity;

public interface RentAreaRespository {
	public List<RentAreaEntity> getRentArea(Long buildingId);
}
