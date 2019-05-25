package com.security.authorization.dto;

public class UsersBuyingStationWarehouseDTO {

	private String username;
	private Long buyingStationId;
	private Long warehouseId;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Long getBuyingStationId() {
		return buyingStationId;
	}
	public void setBuyingStationId(Long buyingStationId) {
		this.buyingStationId = buyingStationId;
	}
	public Long getWarehouseId() {
		return warehouseId;
	}
	public void setWarehouseId(Long warehouseId) {
		this.warehouseId = warehouseId;
	}
	@Override
	public String toString() {
		return "UsersBuyingStationWarehouse [username=" + username
				+ ", buyingStationId=" + buyingStationId + ", warehouseId="
				+ warehouseId + "]";
	}
}
