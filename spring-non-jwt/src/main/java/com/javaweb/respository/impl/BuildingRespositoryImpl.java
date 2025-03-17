package com.javaweb.respository.impl;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.respository.BuildingRepository;
import com.javaweb.respository.Entity.BuildingEntity;
import com.javaweb.util.ConnectionDriverUtils;
import com.javaweb.util.StringUtils;

//@Primary
@Repository
public class BuildingRespositoryImpl implements BuildingRepository {
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

	@Override
	public List<BuildingEntity> findAll(BuildingSearchBuilder buildingSearchBuilder) {
		StringBuilder sql = new StringBuilder("SELECT distinct b.* FROM building b ");
		StringBuilder where = new StringBuilder(" where 1 = 1 ");
		buildJoinClause(buildingSearchBuilder, sql);
		buildCondition(buildingSearchBuilder, where);
		sql.append(where);
		sql.append(" order by b.createddate DESC");
		List<BuildingEntity> results = new ArrayList<BuildingEntity>();
		try (Connection con = ConnectionDriverUtils.getConnection()) {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql.toString());
			while (rs.next()) {
				BuildingEntity building = new BuildingEntity();
				building.setId(rs.getLong("id"));
				building.setName(rs.getString("name"));
				building.setStreet(rs.getString("street"));
				building.setWard(rs.getString("ward"));
				building.setDistrictId(rs.getLong("districtid"));
				building.setStructure(rs.getString("structure"));
				building.setNumberOfBasement(rs.getLong("numberofbasement"));
				building.setFloorArea(rs.getLong("floorarea"));
				building.setDirection(rs.getString("direction"));
				building.setLevel(rs.getString("level"));
				building.setRentPrice(rs.getLong("rentprice"));
				building.setRentPriceDescription(rs.getString("rentpricedescription"));
				building.setServiceFee(rs.getString("servicefee"));
				building.setCarFee(rs.getString("carfee"));
				building.setMotorbikeFee(rs.getString("motorbikefee"));
				building.setOvertimeFee(rs.getString("overtimefee"));
				building.setWaterFee(rs.getString("waterfee"));
				building.setElectricityFee(rs.getString("electricityfee"));
				building.setDeposit(rs.getString("deposit"));
				building.setPayment(rs.getString("payment"));
				building.setRentTime(rs.getString("renttime"));
				building.setDecorationTime(rs.getString("decorationtime"));
				building.setBrokerageFee(rs.getDouble("brokeragefee"));
				building.setNote(rs.getString("note"));
				building.setLinkOfBuilding(rs.getString("linkofbuilding"));
				building.setMap(rs.getString("map"));
				building.setImage(rs.getString("image"));
				building.setCreatedDate(rs.getDate("createddate"));
				building.setModifiedDate(rs.getDate("modifieddate"));
				building.setCreatedBy(rs.getString("createdby"));
				building.setModifiedBy(rs.getString("modifiedby"));
				building.setManagerName(rs.getString("managername"));
				building.setManagerPhoneNumber(rs.getString("managerphonenumber"));
				results.add(building);
			}
		} catch (SQLException ex) {
			System.out.print("Connected database failed");
			ex.printStackTrace();
		}
		return results;
	}
}
