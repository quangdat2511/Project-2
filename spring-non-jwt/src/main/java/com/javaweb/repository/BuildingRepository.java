package com.javaweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaweb.repository.Entity.BuildingEntity;

public interface BuildingRepository extends JpaRepository<BuildingEntity, Long>, BuildingRepositoryCustom {
	List<BuildingEntity> findByNameContaining(String keySearch);

	List<BuildingEntity> findByNameContainingAndDistrictEntity_Id(String keySearch, Long districtId);

	List<BuildingEntity> findByNameContainingAndDistrictEntity_IdAndRentAreaEntities_ValueIn(String keySearch,
			Long districtId, List<Long> rentAreas);

	List<BuildingEntity> findDistinctByNameContainingAndDistrictEntity_IdAndRentAreaEntities_ValueGreaterThan(
			String keySearch, Long districtId, Long rentArea);
	void deleteAllByNameContaining(String keyDelete);
}
