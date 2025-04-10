	package com.javaweb.repository;


import com.javaweb.repository.Entity.DistrictEntity;

public interface DistrictRepository {
	public DistrictEntity getDistrictAccordingToDistrictId(Long districtId);
}
