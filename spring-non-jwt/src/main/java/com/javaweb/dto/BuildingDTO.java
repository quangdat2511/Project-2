package com.javaweb.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;


public class BuildingDTO {
	private Long id;
	private Long districtId;
	@JsonProperty(value = "name_Building")
	private String name;
	private Long numberOfBasement;
	private Long rentPrice;
	private List<String> typeCode;
	private List<Long> rentArea;
	private String street;
	private String ward;
	public Long getId() {
		return id;
	}
	public void setId(Long id) { 
		this.id = id;
	}
	
	public Long getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
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
	public List<Long> getRentArea() {
		return rentArea;
	}
	public void setRentArea(List<Long> rentArea) {
		this.rentArea = rentArea;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getWard() {
		return ward;
	}
	public void setWard(String ward) {
		this.ward = ward;
	}
	
	
	
}
