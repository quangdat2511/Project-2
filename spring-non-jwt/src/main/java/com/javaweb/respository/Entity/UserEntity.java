package com.javaweb.respository.Entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.ManyToAny;

@Entity
@Table(name = "user")
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "username")	
	private String userName;
	@Column(name = "password")	
	private String password;
	@Column(name = "fullname")	
	private String fullname;
	
//	@OneToMany(mappedBy = "userEntity")
//	private List<UserRoleEntity> userRoleEntity;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_role", 
			  joinColumns = @JoinColumn(name = "userid"),
			  inverseJoinColumns = @JoinColumn(name = "roleid"))
	private List<RoleEntity> roleEntities;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
//	public List<UserRoleEntity> getUserRoleEntity() {
//		return userRoleEntity;	
//	}
//	public void setUserRoleEntity(List<UserRoleEntity> userRoleEntity) {
//		this.userRoleEntity = userRoleEntity;
//	}
	public List<RoleEntity> getRoleEntities() {
		return roleEntities;
	}
	public void setRoleEntities(List<RoleEntity> roleEntities) {
		this.roleEntities = roleEntities;
	}
	
}
