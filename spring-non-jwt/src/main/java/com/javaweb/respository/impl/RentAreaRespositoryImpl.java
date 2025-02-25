package com.javaweb.respository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.javaweb.respository.RentAreaRespository;

@Repository
public class RentAreaRespositoryImpl implements RentAreaRespository{
	static final String DB_URL = "jdbc:mysql://localhost:3306/estatebasic";
	static final String USER = "root";
	static final String PASS = "123456";
	@Override
	public List<Integer> getRentArea(Map<String, Object> params, Long buildingId){
		String rentAreaFrom = (String)params.get("rentAreaFrom");
		String rentAreaTo = (String)params.get("rentAreaTo");	
		List<Integer> results = new ArrayList<Integer>();
		StringBuilder sql = new StringBuilder("SELECT * FROM rentArea where buildingid = " + buildingId.toString());
		if (rentAreaFrom != null && !rentAreaFrom.equals("")) {
			sql.append(" AND value >= " + rentAreaFrom);			
		}
		if (rentAreaTo != null && !rentAreaTo.equals("")) {
			sql.append(" AND value <= " + rentAreaTo);
		}
		try(Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
				Statement st = con.createStatement();
					ResultSet rs = st.executeQuery(sql.toString());){
			while (rs.next()) 
			{
				Integer rentArea = rs.getInt("value");
				results.add(rentArea);
			}
		}	
		catch(SQLException ex) {
			System.out.print("Connected database failed");
			ex.printStackTrace();
		}
		return results;
	}

}
