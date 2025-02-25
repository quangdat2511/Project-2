package com.javaweb.service.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.dto.response.BuildingResponseDTO;
import com.javaweb.respository.BuildingRepository;
import com.javaweb.respository.DistrictRepository;
import com.javaweb.respository.RentAreaRespository;
import com.javaweb.respository.Entity.BuildingEntity;
import com.javaweb.service.BuildingService;

@Service  
public class BuildingServiceImpl implements BuildingService{
	@Autowired	
	private BuildingRepository buildingRepository;
	@Autowired	
	private DistrictRepository districtRepository; 
	@Autowired	
	private RentAreaRespository rentAreaRespository;
@Override
public List<BuildingResponseDTO> findAll(Map<String, Object> params, List<String> typeCode) {
	List<BuildingEntity> buildingEntities = buildingRepository.findAll(params, typeCode);
	//filter
	List<BuildingResponseDTO> results = new ArrayList<>();
	for (BuildingEntity buildingEntity: buildingEntities) {
		BuildingResponseDTO buildingResponse = new BuildingResponseDTO();
		buildingResponse.setName(buildingEntity.getName());
		buildingResponse.setAddress(buildingEntity.getStreet() + "," + buildingEntity.getWard() + "," + districtRepository.getDistrictName(buildingEntity.getDistrictId()));
		buildingResponse.setNumberOfBasement(buildingEntity.getNumberOfBasement());
		buildingResponse.setManagerName(buildingEntity.getManagerName());
		buildingResponse.setManagerPhoneNumber(buildingEntity.getManagerPhoneNumber());
		buildingResponse.setFloorArea(buildingEntity.getFloorArea());
		buildingResponse.setAvailableArea(null);
		List<Integer> listRentArea = rentAreaRespository.getRentArea(params, buildingEntity.getId());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < listRentArea.size(); i++) {
            sb.append(listRentArea.get(i));
            if (i < listRentArea.size() - 1) {
                sb.append(",");
            }
        }
        buildingResponse.setRentArea(sb.toString());
        buildingResponse.setRentPrice(buildingEntity.getRentPrice());
        buildingResponse.setServiceFee(buildingEntity.getServiceFee());       
        buildingResponse.setBrokerageFee(buildingEntity.getBrokerageFee());
		results.add(buildingResponse);
	}	
	return results;		
}
@Override
public String delete(List<Long> ids) {
	// TODO Auto-generated method stub
	return null;
}
}
	