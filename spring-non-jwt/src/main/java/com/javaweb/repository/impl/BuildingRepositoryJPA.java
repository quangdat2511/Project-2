package com.javaweb.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.Entity.BuildingEntity;

//@Primary
@Repository
public class BuildingRepositoryJPA implements BuildingRepository{

	@PersistenceContext EntityManager entityManager;
	 
//	@Override
	public List<BuildingEntity> findAll(BuildingSearchBuilder buildingSearchBuilder) {
		String sql = "select distinct b from BuildingEntity b join b.rentAreaEntities r where 1 = 1 and b.name like :nameBuilding and b.districtEntity.code like 'Q1'" +
	" and r.value>= :value";
		TypedQuery<BuildingEntity> query = entityManager.createQuery(sql, BuildingEntity.class);
		query.setParameter("value", 200L);
		query.setParameter("nameBuilding", "%Tower%");
		return query.getResultList();
	}	

}
