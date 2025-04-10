package com.javaweb.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.dto.BuildingDTO;
import com.javaweb.dto.response.BuildingResponseDTO;
import com.javaweb.myexception.ValidateDataException;
import com.javaweb.respository.Entity.BuildingEntity;
import com.javaweb.service.BuildingService;

@RestController
@RequestMapping("/api/buildings")
public class BuildingAPI {
	@Autowired
	private BuildingService buildingService;
	@GetMapping
	public Object getBuilding(@RequestParam Map<String, Object> params, @RequestParam(name = "typeCode", required = false) List<String> typeCode){
		List<BuildingResponseDTO> results = buildingService.findAll(params, typeCode);
		return results;
	}
	public static void validateDataBuilding(BuildingDTO buildingDTO){
		if (buildingDTO.getName().isBlank() || buildingDTO.getNumberOfBasement() == null)
			throw new ValidateDataException("name or number of basement is null");
	}
	@PostMapping
	public Object createBuilding(@RequestBody BuildingDTO buildingDTO) {
		validateDataBuilding(buildingDTO);
		BuildingEntity buildingEntity = buildingService.createBuilding(buildingDTO);
		return buildingEntity;
	}
	@PutMapping
	public Object updateBuilding(@RequestBody BuildingDTO buildingDTO) {
		validateDataBuilding(buildingDTO);
		if (buildingDTO.getId() == null)
			throw new ValidateDataException("id can not be null");			
		BuildingEntity buildingEntity = buildingService.updateBuilding(buildingDTO);
		return buildingEntity;
	}	
	@DeleteMapping("{ids}")
	public void deleteBuilding(@PathVariable List<Long> ids){
		System.out.print("Delete building id = " + ids);
		String results = buildingService.delete(ids);
		System.out.println(results);
	}
}
