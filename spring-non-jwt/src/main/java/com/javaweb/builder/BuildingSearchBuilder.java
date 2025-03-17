package com.javaweb.builder;

import java.util.List;

public class BuildingSearchBuilder {
    private String name;
    private Long floorArea;
    private Long districtId;
    private String ward;
    private String street;
    private Long numberOfBasement;
    private String direction;
    private String level;
    private Long rentAreaFrom;
    private Long rentAreaTo;
    private Long rentPriceFrom;
    private Long rentPriceTo;
    private String managerName;
    private String managerPhoneNumber;
    private Long staffId;
    private List<String> typeCode;
	public String getName() {
		return name;
	}
	public Long getFloorArea() {
		return floorArea;
	}
	public Long getDistrictId() {
		return districtId;
	}
	public String getWard() {
		return ward;
	}
	public String getStreet() {
		return street;
	}
	public Long getNumberOfBasement() {
		return numberOfBasement;
	}
	public String getDirection() {
		return direction;
	}
	public String getLevel() {
		return level;
	}
	public Long getRentAreaFrom() {
		return rentAreaFrom;
	}
	public Long getRentAreaTo() {
		return rentAreaTo;
	}
	public Long getRentPriceFrom() {
		return rentPriceFrom;
	}
	public Long getRentPriceTo() {
		return rentPriceTo;
	}
	public String getManagerName() {
		return managerName;
	}
	public String getManagerPhoneNumber() {
		return managerPhoneNumber;
	}
	public Long getStaffId() {
		return staffId;
	}
	public List<String> getTypeCode() {
		return typeCode;
	}
	public BuildingSearchBuilder(Builder builder) {
		this.name = builder.name;
		this.floorArea = builder.floorArea;
		this.districtId = builder.districtId;
		this.ward = builder.ward;
		this.street = builder.street;
		this.numberOfBasement = builder.numberOfBasement;
		this.direction = builder.direction;
		this.level = builder.level;
		this.rentAreaFrom = builder.rentAreaFrom;
		this.rentAreaTo = builder.rentAreaTo;
		this.rentPriceFrom = builder.rentPriceFrom;
		this.rentPriceTo = builder.rentPriceTo;
		this.managerName = builder.managerName;
		this.managerPhoneNumber = builder.managerPhoneNumber;
		this.staffId = builder.staffId;
		this.typeCode = builder.typeCode;
	}
	public static class Builder{
        private String name;
        private Long floorArea;
        private Long districtId;
        private String ward;
        private String street;
        private Long numberOfBasement;
        private String direction;
        private String level;
        private Long rentAreaFrom;
        private Long rentAreaTo;
        private Long rentPriceFrom;
        private Long rentPriceTo;
        private String managerName;
        private String managerPhoneNumber;
        private Long staffId;
        private List<String> typeCode;
		public Builder name(String name) {
			this.name = name;
			return this;
		}
		public Builder floorArea(Long floorArea) {
			this.floorArea = floorArea;
			return this;
		}
		public Builder districtId(Long districtId) {
			this.districtId = districtId;
			return this;
		}
		public Builder ward(String ward) {
			this.ward = ward;
			return this;
		}
		public Builder street(String street) {
			this.street = street;
			return this;
		}
		public Builder numberOfBasement(Long numberOfBasement) {
			this.numberOfBasement = numberOfBasement;
			return this;
		}
		public Builder direction(String direction) {
			this.direction = direction;
			return this;
		}
		public Builder level(String level) {
			this.level = level;
			return this;
		}
		public Builder rentAreaFrom(Long rentAreaFrom) {
			this.rentAreaFrom = rentAreaFrom;
			return this;
		}
		public Builder rentAreaTo(Long rentAreaTo) {
			this.rentAreaTo = rentAreaTo;
			return this;
		}
		public Builder rentPriceFrom(Long rentPriceFrom) {
			this.rentPriceFrom = rentPriceFrom;
			return this;
		}
		public Builder rentPriceTo(Long rentPriceTo) {
			this.rentPriceTo = rentPriceTo;
			return this;
		}
		public Builder managerName(String managerName) {
			this.managerName = managerName;
			return this;
		}
		public Builder managerPhoneNumber(String managerPhoneNumber) {
			this.managerPhoneNumber = managerPhoneNumber;
			return this;
		}
		public Builder staffId(Long staffId) {
			this.staffId = staffId;
			return this;
		}
		public Builder typeCode(List<String> typeCode) {
			this.typeCode = typeCode;
			return this;
		}   	
		public BuildingSearchBuilder build() {
			return new BuildingSearchBuilder(this);
		}
    }
}