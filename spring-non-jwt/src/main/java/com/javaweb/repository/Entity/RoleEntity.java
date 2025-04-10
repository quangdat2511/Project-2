package com.javaweb.repository.Entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class RoleEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "code", unique = true, nullable = false)
	private String code;
	@Column(name = "name", unique = true, nullable = false)
	private String name;
//	@OneToMany(mappedBy = "roleEntity")
//	private List<UserRoleEntity> roleUserEntity;
	@ManyToMany(mappedBy = "roleEntities", fetch = FetchType.LAZY)
	private List<UserEntity> userEntities;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
//	public List<UserRoleEntity> getRoleUserEntity() {
//		return roleUserEntity;
//	}
//	public void setRoleUserEntity(List<UserRoleEntity> roleUserEntity) {
//		this.roleUserEntity = roleUserEntity;
//	}
//	
	public List<UserEntity> getUserEntities() {
		return userEntities;
	}
	public void setUserEntities(List<UserEntity> userEntities) {
		this.userEntities = userEntities;
	}
	
}
