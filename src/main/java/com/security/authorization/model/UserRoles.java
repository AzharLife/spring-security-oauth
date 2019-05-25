package com.security.authorization.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name="user_role")
public class UserRoles {

	@Id
	@Column(name="username")
	private String userName;
	
	@Column(name="role_name")
	private String roleName;
	

	@Type(type = "org.hibernate.type.NumericBooleanType")
	@Column(name="deleted_flag")
	private Boolean deletedFlag;
	
	@Column(name="priority_order")
	private Long priorityOrder;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Boolean getDeletedFlag() {
		return deletedFlag;
	}

	public void setDeletedFlag(Boolean deletedFlag) {
		this.deletedFlag = deletedFlag;
	}

	public Long getPriorityOrder() {
		return priorityOrder;
	}

	public void setPriorityOrder(Long priorityOrder) {
		this.priorityOrder = priorityOrder;
	}
	
	
	
}
