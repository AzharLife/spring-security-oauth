package com.security.authorization.dto;

public class UserRolesDTO {

	private String roleName;
	
	private Long priorityOrder;

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Long getPriorityOrder() {
		return priorityOrder;
	}

	public void setPriorityOrder(Long priorityOrder) {
		this.priorityOrder = priorityOrder;
	}
	
	
}
