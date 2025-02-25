package com.javaweb.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;


public class BuildingDTO {
	@JsonProperty(value = "name_Building")
	private String name;
	private Long numberOfBasement;
	private Long rentPrice;
	private List<String> typeCode;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getNumberOfBasement() {
		return numberOfBasement;
	}
	public void setNumberOfBasement(Long numberOfBasement) {
		this.numberOfBasement = numberOfBasement;
	}
	public Long getRentPrice() {
		return rentPrice;
	}
	public void setRentPrice(Long rentPrice) {
		this.rentPrice = rentPrice;
	}
	public List<String> getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(List<String> typeCode) {
		this.typeCode = typeCode;
	}
	
}
