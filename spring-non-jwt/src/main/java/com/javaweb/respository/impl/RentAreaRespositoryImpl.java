package com.javaweb.respository.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.javaweb.respository.RentAreaRespository;
import com.javaweb.respository.Entity.RentAreaEntity;
import com.javaweb.util.ConnectionDriverUtils;
@Repository
public class RentAreaRespositoryImpl implements RentAreaRespository{
	@Override
	public List<RentAreaEntity> getRentArea(Map<String, Object> params, Long buildingId){
		String rentAreaFrom = (String)params.get("rentAreaFrom");
		String rentAreaTo = (String)params.get("rentAreaTo");	
		List<RentAreaEntity> results = new ArrayList<RentAreaEntity>();
		StringBuilder sql = new StringBuilder("SELECT * FROM rentArea where buildingid = " + buildingId.toString());
		if (rentAreaFrom != null && !rentAreaFrom.equals("")) {
			sql.append(" AND value >= " + rentAreaFrom);			
		}
		if (rentAreaTo != null && !rentAreaTo.equals("")) {
			sql.append(" AND value <= " + rentAreaTo);
		}
		try(Connection con = ConnectionDriverUtils.getConnection()){
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql.toString());
			while (rs.next()) 
			{
				RentAreaEntity rentAreaEntity = new RentAreaEntity();
				rentAreaEntity.setId(rs.getLong("id"));
				rentAreaEntity.setValue(rs.getLong("value"));
				rentAreaEntity.setBuildingId(rs.getLong("buildingid"));
				rentAreaEntity.setCreatedDate(rs.getDate("createddate"));
				rentAreaEntity.setModifiedDate(rs.getDate("modifieddate"));
				rentAreaEntity.setCreatedBy(rs.getString("createdby"));
				rentAreaEntity.setModifiedBy(rs.getString("modifiedby"));
				results.add(rentAreaEntity);
			}
		}	
		catch(SQLException ex) {
			System.out.print("Connected database failed");
			ex.printStackTrace();
		}
		return results;
	}

}
