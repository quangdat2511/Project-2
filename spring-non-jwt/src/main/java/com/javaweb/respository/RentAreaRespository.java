package com.javaweb.respository;

import java.util.List;
import java.util.Map;

import com.javaweb.respository.Entity.RentAreaEntity;

public interface RentAreaRespository {
	public List<RentAreaEntity> getRentArea(Map<String, Object> params, Long buildingId);
}
