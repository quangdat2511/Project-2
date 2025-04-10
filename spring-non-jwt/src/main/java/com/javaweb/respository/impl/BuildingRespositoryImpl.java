package com.javaweb.respository.impl;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.respository.BuildingRepository;
import com.javaweb.respository.Entity.BuildingEntity;
import com.javaweb.util.StringUtils;

@Primary
@Repository
public class BuildingRespositoryImpl{
	@PersistenceContext
	private EntityManager entityManager;
	private void buildJoinClause(BuildingSearchBuilder buildingSearchBuilder, StringBuilder join) {
		if (buildingSearchBuilder.getStaffId() != null) {
			join.append(" join assignmentbuilding asbd on asbd.buildingid = b.id");
		}
		if (buildingSearchBuilder.getTypeCode() != null && !buildingSearchBuilder.getTypeCode().isEmpty()) {
			join.append(" join buildingrenttype brt on b.id = brt.buildingid ");
			join.append(" join renttype rt on brt.renttypeid = rt.id");
		}
		if (buildingSearchBuilder.getRentAreaFrom() != null || buildingSearchBuilder.getRentAreaTo() != null) {
			join.append(" join rentarea on rentarea.buildingid = b.id");
		}

	}

	private void buildCondition(BuildingSearchBuilder buildingSearchBuilder, StringBuilder where) {
		try {
			Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(true);
				String key = field.getName();
				if (!key.equals("staffId") && !key.equals("typeCode") && !key.startsWith("rentArea")
						&& !key.startsWith("rentPrice")) {
					Object value = field.get(buildingSearchBuilder);
					if (value != null) {
						if (StringUtils.isNumber(value.toString())) {
							where.append(" AND b." + key + " = " + value.toString());
						} else {
							where.append(" AND b." + key + " Like '%" + value.toString() + "%'");
						}
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		// for (Map.Entry<String, Object> item : params.entrySet()) {
//			String key = item.getKey();
//			if (!key.equals("staffId") && !key.equals("typeCode") && !key.startsWith("rentArea") && !key.startsWith("rentPrice")) {
//				Object value = item.getValue();
//				if (StringUtils.isNotBlank(value.toString())) {
//					 if (StringUtils.isNumber(value.toString())) {
//						 where.append(" AND b." + key + " = " + value.toString());
//					 }
//					 else {
//						 where.append(" AND b." + key + " Like '%" + value.toString() + "%'");
//					 }
//				}
//			}
//		}
		Long staffId = buildingSearchBuilder.getStaffId();
		if (staffId != null) {
			where.append(" and asbd.staffId = " + staffId);
		}
		Long rentAreaFrom = buildingSearchBuilder.getRentAreaFrom();
		Long rentAreaTo = buildingSearchBuilder.getRentAreaTo();
		if (rentAreaFrom != null) {
			where.append(" AND rentarea.value >= " + rentAreaFrom);
		}
		if (rentAreaTo != null) {
			where.append(" AND rentarea.value <= " + rentAreaTo);
		}
		List<String> typeCode = buildingSearchBuilder.getTypeCode();
		if (typeCode != null && !typeCode.isEmpty()) {
			where.append(" AND rt.code IN (");
			where.append(typeCode.stream().map(code -> "'" + code + "'").collect(Collectors.joining(", ")));
			where.append(")");
		}
		Long rentPriceFrom = buildingSearchBuilder.getRentAreaFrom();
		Long rentPriceTo = buildingSearchBuilder.getRentAreaTo();
		if (rentPriceFrom != null) {
			where.append(" AND b.rentPrice >= " + rentPriceFrom);
		}
		if (rentPriceTo != null) {
			where.append(" AND b.rentPrice <= " + rentPriceTo);
		}
	}

	//	@Override
	public List<BuildingEntity> findAll(BuildingSearchBuilder buildingSearchBuilder) {
		StringBuilder sql = new StringBuilder("SELECT distinct b.* FROM building b ");
		StringBuilder where = new StringBuilder(" where 1 = 1 ");
		buildJoinClause(buildingSearchBuilder, sql);
		buildCondition(buildingSearchBuilder, where);
		sql.append(where);
		sql.append(" order by b.createddate DESC");
		Query query = entityManager.createNativeQuery(sql.toString(), BuildingEntity.class);
		return query.getResultList();	
	}
}
