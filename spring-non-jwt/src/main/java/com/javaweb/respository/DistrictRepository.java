	package com.javaweb.respository;


import com.javaweb.respository.Entity.DistrictEntity;

public interface DistrictRepository {
	public DistrictEntity getDistrictAccordingToDistrictId(Long districtId);
}
