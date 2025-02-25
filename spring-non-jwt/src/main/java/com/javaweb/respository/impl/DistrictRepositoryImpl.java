package com.javaweb.respository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Repository;

import com.javaweb.respository.DistrictRepository;
@Repository
public class DistrictRepositoryImpl implements DistrictRepository{
	static final String DB_URL = "jdbc:mysql://localhost:3306/estatebasic";
	static final String USER = "root";
	static final String PASS = "123456";
	@Override
	public String getDistrictName(Long districtId){
		StringBuilder sql = new StringBuilder("SELECT * FROM district where id = " + districtId.toString());
		try(Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
				Statement st = con.createStatement();
					ResultSet rs = st.executeQuery(sql.toString());){
			if (rs.next()) {
				return rs.getString("name");
			}
		}
		catch(SQLException ex) {
			System.out.print("Connected database failed");
			ex.printStackTrace();
		}
		return null;
	}
}
