package com.javaweb.convert;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.javaweb.dto.response.BuildingResponseDTO;
import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.RentAreaRespository;
import com.javaweb.repository.Entity.BuildingEntity;
import com.javaweb.repository.Entity.DistrictEntity;
import com.javaweb.repository.Entity.RentAreaEntity;

@Component
public class BuildingConverter {
	@Autowired	
	private DistrictRepository districtRepository; 		
	@Autowired	
	private RentAreaRespository rentAreaRespository;
	@Autowired 
	private ModelMapper modelMapper;
	public BuildingResponseDTO toBuildingSearchResponseDTO(BuildingEntity buildingEntity) {
		BuildingResponseDTO buildingResponse = modelMapper.map(buildingEntity, BuildingResponseDTO.class);
//		buildingResponse.setName(buildingEntity.getName());
		DistrictEntity districtEntity = districtRepository.getDistrictAccordingToDistrictId(buildingEntity.getDistrictId());
		buildingResponse.setAddress(buildingEntity.getStreet() + "," + buildingEntity.getWard() + "," + districtEntity.getName());
//		buildingResponse.setNumberOfBasement(buildingEntity.getNumberOfBasement());
//		buildingResponse.setManagerName(buildingEntity.getManagerName());
//		buildingResponse.setManagerPhoneNumber(buildingEntity.getManagerPhoneNumber());
//		buildingResponse.setFloorArea(buildingEntity.getFloorArea());
		buildingResponse.setAvailableArea(null);
		List<RentAreaEntity> listRentArea = rentAreaRespository.getRentArea(buildingEntity.getId());
        String rentAreaAsString = listRentArea.stream().map(rentArea -> String.valueOf(rentArea.getValue())).collect(Collectors.joining(", "));
        buildingResponse.setRentArea(rentAreaAsString);
//        buildingResponse.setRentPrice(buildingEntity.getRentPrice());
//        buildingResponse.setServiceFee(buildingEntity.getServiceFee());       
//        buildingResponse.setBrokerageFee(buildingEntity.getBrokerageFee());
        return buildingResponse;
	}
}
