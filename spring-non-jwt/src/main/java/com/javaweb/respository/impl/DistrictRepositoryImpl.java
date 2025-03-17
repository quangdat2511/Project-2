		package com.javaweb.respository.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Repository;

import com.javaweb.respository.DistrictRepository;
import com.javaweb.respository.Entity.DistrictEntity;
import com.javaweb.util.ConnectionDriverUtils;

@Repository
public class DistrictRepositoryImpl implements DistrictRepository {

	@Override
	public DistrictEntity getDistrictAccordingToDistrictId(Long districtId) {
		StringBuilder sql = new StringBuilder("SELECT * FROM district where id = " + districtId.toString());
		try (Connection con = ConnectionDriverUtils.getConnection();) {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql.toString());
			if (rs.next()) {
				DistrictEntity districtEntity = new DistrictEntity();
				districtEntity.setId(rs.getLong("id"));
				districtEntity.setCode(rs.getString("code"));
				districtEntity.setName(rs.getString("name"));
				return districtEntity;
			}
		} catch (SQLException ex) {
			System.out.print("Connected database failed");
			ex.printStackTrace();
		}
		return null;
	}
}
