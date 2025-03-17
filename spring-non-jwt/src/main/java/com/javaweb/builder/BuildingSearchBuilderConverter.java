package com.javaweb.builder;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.javaweb.util.MapUtils;

@Component
public class BuildingSearchBuilderConverter {
	public static BuildingSearchBuilder toBuildingSearchBuilder(Map <String, Object> params, List<String> typeCode) {
		BuildingSearchBuilder buildingSearchBuilder = new BuildingSearchBuilder.Builder().name(MapUtils.getObject(params, "name", String.class))
																						 .districtId(MapUtils.getObject(params, "districtId", Long.class))
																						 .direction(MapUtils.getObject(params, "direction", String.class))
																						 .floorArea(MapUtils.getObject(params, "floorArea", Long.class))
																						 .level(MapUtils.getObject(params, "level", String.class))
																						 .managerName(MapUtils.getObject(params, "managerName", String.class))
																						 .managerPhoneNumber(MapUtils.getObject(params, "managerPhoneNumber", String.class))
																						 .numberOfBasement(MapUtils.getObject(params, "numberOfBasement", Long.class))
																						 .rentAreaFrom(MapUtils.getObject(params, "rentAreaFrom", Long.class))
																						 .rentAreaTo(MapUtils.getObject(params, "rentAreaTo", Long.class))
																						 .rentPriceFrom(MapUtils.getObject(params, "rentPriceFrom", Long.class))
																						 .rentPriceTo(MapUtils.getObject(params, "rentPriceTo", Long.class))
																						 .staffId(MapUtils.getObject(params, "staffId", Long.class))
																						 .ward(MapUtils.getObject(params, "ward", String.class))
																						 .typeCode(typeCode)
																						 .street(MapUtils.getObject(params, "street", String.class))
																						 .build();
		return buildingSearchBuilder;
																						 
	}
}
