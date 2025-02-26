package com.javaweb.respository.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.javaweb.respository.BuildingRepository;
import com.javaweb.respository.Entity.BuildingEntity;
import com.javaweb.util.ConnectionDriverUtils;
//@Primary
@Repository
public class BuildingRespositoryImpl implements BuildingRepository{
	StringBuilder buildJoinClause(Map<String, Object> params, List<String> typeCode) {
		StringBuilder sql = new StringBuilder("");
		String rentAreaFrom = (String)params.get("rentAreaFrom");
		String rentAreaTo = (String)params.get("rentAreaTo");
		String staffId = (String)params.get("staffId");
		if (rentAreaFrom != null && !rentAreaFrom.equals("") || rentAreaTo != null && !rentAreaTo.equals("")) {
			sql.append(" join rentarea on rentarea.buildingid = b.id");
		}
		if (typeCode != null && !typeCode.isEmpty()) {
			sql.append(" join buildingrenttype on b.id = buildingrenttype.buildingid join renttype on buildingrenttype.renttypeid = renttype.id");
		}
		boolean staffIdIsOk = staffId != null && !staffId.equals("");
		if (staffIdIsOk) {
			sql.append(" join assignmentbuilding asbd on asbd.buildingid = b.id");
		}
		return sql;
	}
	StringBuilder buildWhereClause(Map<String, Object> params, List<String> typeCode) {
		StringBuilder sql = new StringBuilder("");
		String nameBuilding = (String)params.get("name");
		String floorArea = (String)params.get("floorArea");
		String districtId = (String)params.get("districtId");
		String ward = (String)params.get("ward");
		String street = (String)params.get("street");
		String numberOfBasement = (String)params.get("numberOfBasement");
		String direction = (String)params.get("direction");
		String level = (String)params.get("level");	
		String rentAreaFrom = (String)params.get("rentAreaFrom");
		String rentAreaTo = (String)params.get("rentAreaTo");
		String rentPriceFrom = (String)params.get("rentPriceFrom ");
		String rentPriceTo = (String)params.get("rentPriceTo");
		String managerName = (String)params.get("managerName");
		String managerPhoneNumber = (String)params.get("managerPhoneNumber");
		String staffId = (String)params.get("staffId");
		if (nameBuilding != null && !nameBuilding.equals("")) {
			sql.append(" AND b.name like '%" + nameBuilding + "%'");	
		}			
		if (floorArea != null && !floorArea.equals("")) {
			sql.append(" AND b.floorarea = " + floorArea);
		}
		if (districtId != null && !districtId.equals("")) {
			sql.append(" AND b.districtid = " + districtId);
		}
		if (ward != null && !ward.equals("")) {
			sql.append(" AND b.ward like '%" + ward + "%'");	
		}
		if (street != null && !street.equals("")) {
			sql.append(" AND b.street like '%" + street + "%'");	
		}
		if (numberOfBasement != null && !numberOfBasement.equals("")) {
			sql.append(" AND b.numberofbasement = " + numberOfBasement);				
		}
		if (direction != null && !direction.equals("")) {
			sql.append(" AND b.direction like '%" + direction + "%'");	
		}
		if (level != null && !level.equals("")) {
			sql.append(" AND b.level = " + level);	
		}
		if (rentAreaFrom != null && !rentAreaFrom.equals("")) {
			sql.append(" AND rentarea.value >= " + rentAreaFrom);			
		}
		if (rentAreaTo != null && !rentAreaTo.equals("")) {
			sql.append(" AND rentarea.value <= " + rentAreaTo);
		}
		if (rentPriceFrom != null && !rentPriceFrom.equals("")) {
			sql.append(" AND b.rentprice >= " + rentPriceFrom);
		}
		if (rentPriceTo != null && !rentPriceTo.equals("")) {
			sql.append(" AND b.rentprice <= " + rentPriceTo);
		}
		if (managerName != null && !managerName.equals("")) {
			sql.append(" AND b.managername like '%" + managerName + "%'");	
		}
		if (managerPhoneNumber != null && !managerPhoneNumber.equals("")) {
			sql.append(" AND b.managerphonenumber like '%" + managerPhoneNumber + "%'");	
		}
		if (staffId != null && !staffId.equals("")) {
			sql.append(" AND asbd.staffid = " + staffId);			
		}
		if (typeCode != null && !typeCode.isEmpty()) {
			sql.append(" AND renttype.code IN (");
			for (int i = 0; i < typeCode.size(); i++) {
				sql.append("'" + typeCode.get(i) + "'");
				if (i != typeCode.size() - 1) {
					sql.append(",");
				}
			}
			sql.append(")");
		}
		return sql;
	}
	@Override
	public List<BuildingEntity> findAll(Map<String, Object> params, List<String> typeCode){
		StringBuilder sql = new StringBuilder("SELECT distinct b.* FROM building b ");
		sql.append(buildJoinClause(params, typeCode));
		sql.append(" where 1 = 1 ");
		sql.append(buildWhereClause(params, typeCode));	
		List<BuildingEntity> results = new ArrayList<BuildingEntity>();
		try(Connection con = ConnectionDriverUtils.getConnection()){
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql.toString());
			while (rs.next()) 
			{
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
		}	
		catch(SQLException ex) {
			System.out.print("Connected database failed");
			ex.printStackTrace();
		}
		return results;
	}
}
