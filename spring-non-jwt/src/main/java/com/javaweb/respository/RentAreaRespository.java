package com.javaweb.respository;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface RentAreaRespository {
	public List<Integer> getRentArea(Map<String, Object> params, Long buildingId);
}
